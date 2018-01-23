package com.kuliao.horcrux.app.util;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class WaterMarkerImageUtil {




        /**
         * 缩略图生成方法
         *
         *
         */
        public static BufferedImage waterMarkImage(String fileName,String fileId,String size) {
            //获取指定缩略图大小


            String[] backSize = size.split("x");
            int width = Integer.parseInt(backSize[0]);
            int height = Integer.parseInt(backSize[1]);

            BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

            //给缩略图加水印效果
            Graphics2D g2d = (Graphics2D) image.getGraphics();

            Font font = new Font("Sans", Font.BOLD,width/20);
            Rectangle2D rectangle_name = font.getStringBounds(fileName, g2d.getFontRenderContext());
            Rectangle2D rectangle_id = font.getStringBounds(fileId,g2d.getFontRenderContext());
            if(width < rectangle_id.getWidth() + 6) {

                return null;

            }
            int x_name = width - (int)rectangle_name.getWidth() - 5;
            int y_name = height - 10;
            int x_id = (width - (int)rectangle_id.getWidth())/2;
            int y_id = (height - (int)rectangle_id.getHeight())/2;
            g2d.setFont(font);
            g2d.drawString(fileName, x_name, y_name);
            g2d.drawString(fileId,x_id , y_id);


            return image;
        }


}
