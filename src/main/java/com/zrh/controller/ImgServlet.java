package com.zrh.controller;

import com.alibaba.fastjson.JSONObject;
import com.zrh.constants.Constant;
import com.zrh.entity.User;
import com.zrh.enums.SessionEnum;
import com.zrh.service.UserService;
import com.zrh.utils.ImgCodeUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
@WebServlet("/img/*")
public class ImgServlet extends BaseServlet {

    private UserService userService = new UserService();

    protected void getHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pic = request.getParameter("pic");
        //完整路径=服务器路径+数据库保存的图片名称
        String realUrl = Constant.FILE_PREFIX + pic;
        File file = new File(realUrl);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while (true) {
            len = bis.read(b);
            if (len == -1) {
                break;
            }
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        bis.close();
    }

    protected void updatePic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();

        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 1024 * 2);
        //每个表单域中数据会封装到一个对应的FileItem对象上
        PrintWriter out = response.getWriter();
        //文件全路径
        String path = "";
        //存入数据库的路径
        String pic = "";
        try {
            List<FileItem> items = sfu.parseRequest(request);
            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String name = item.getName();

                    // xxx.jpg xxx.png
                    String suffix = name.split("\\.")[1];
                    // pic = UUID.randomUUID().toString().replace("-", "") + "." + suffix;
                    pic = System.currentTimeMillis() + "." + suffix;
                    // 123123123.jpg
                    path = Constant.FILE_PREFIX + pic;

                    File file = new File(path);
                    if (!file.exists()) {
                        //将文件写出到指定磁盘（即保存图片的服务器）
                        item.write(file);
                    }
                }
            }

            //修改数据库中pic
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute(Constant.SESSION_LOGIN);
            userService.updatePic(loginUser.getId(), pic);

            loginUser.setPic(pic);
            session.setAttribute(Constant.SESSION_LOGIN, loginUser);

            //把pic返还给前端jsp
            map.put("code", "200");
            map.put("msg", pic);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "修改头像失败");
        }

        out.write(JSONObject.toJSONString(map));
    }

    protected void getPicCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();
        BufferedImage image = imgCodeUtil.getImage();

        //获取验证码文本内容
        String code = imgCodeUtil.getText();

        //把图片验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute(SessionEnum.SESSION_PIC_CODE.getValue(), code);

        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();

    }

}

