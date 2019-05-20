import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MytEst {
//    public static void main(String[] args) {
//        int s[] = {2, 3, 4, 5, 6, 7};
//        int m[] = {9, 10, 11};
//        int h[] = {13, 14, 15};
//
//        for (int i = 0; i < m.length; i++) {
//            System.out.println("INSERT INTO `sys_data` (`name`, `type`, `pid`) VALUES \n" +
//                    "('语文', '3', "+m[i]+"),\n" +
//                    "('数学', '3', "+m[i]+"),\n" +
//                    "('英语', '3', "+m[i]+"),\n" +
//                    "('政治', '3', "+m[i]+"),\n" +
//                    "('历史', '3', "+m[i]+"),\n" +
//                    "('体育', '3', "+m[i]+"),\n" +
//                    "('音乐', '3', "+m[i]+"),\n" +
//                    "('美术', '3', "+m[i]+"),\n" +
//                    "('劳技', '3', "+m[i]+"),\n" +
//                    "('生物', '3', "+m[i]+"),\n" +
//                    "('地理', '3', "+m[i]+";");
//        }
//    }

    public static void main(String[] args) {
        try {
            BufferedImage read = ImageIO.read(new File("C:\\Users\\XHX\\Desktop\\791bedd5e1944ff0b77af7f08a9d1481.png"));

            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
