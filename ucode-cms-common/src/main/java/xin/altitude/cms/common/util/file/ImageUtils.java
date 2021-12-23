package xin.altitude.cms.common.util.file;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.altitude.cms.common.config.CmsConfig;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.util.StringUtils;
import xin.altitude.cms.common.util.spring.SpringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Optional;

/**
 * 图片处理工具类
 *
 * @author ucode
 */
public class ImageUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);
    
    public static byte[] getImage(String imagePath) {
        try (InputStream is = getFile(imagePath)) {
            return Optional.ofNullable(is).map(e -> {
                try {
                    return IOUtils.toByteArray(e);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                return null;
            }).orElse(null);
        } catch (IOException e) {
            log.error("图片加载异常 {}", e);
            return null;
        }
    }
    
    public static InputStream getFile(String imagePath) {
        try {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        } catch (Exception e) {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }
    
    /**
     * 读取文件为字节数据
     *
     * @return 字节数据
     */
    public static byte[] readFile(String url) {
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            if (url.startsWith("http")) {
                // 网络地址
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(30 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                in = urlConnection.getInputStream();
            } else {
                // 本机地址
                String localPath = SpringUtils.getBean(CmsConfig.class).getCms().getProfile();
                String downloadPath = localPath + StringUtils.substringAfter(url, Constants.RESOURCE_PREFIX);
                in = new FileInputStream(downloadPath);
            }
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("获取文件路径异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(baos);
        }
    }
}
