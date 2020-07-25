package com.myqrreader.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {
    private final static String charset = "UTF-8";
    private final static String ext = ".png";
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    public static final String PNG_FORMAT = "png";

    private static Map<DecodeHintType, Object> tmpHintsMap = new EnumMap<>(DecodeHintType.class);

    static {
        tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS,
            EnumSet.allOf(BarcodeFormat.class));
        tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
    }

    public static String createQR(String data, String fileName) throws WriterException
    {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        Path path = FileSystems.getDefault().getPath(fileName);
        try
        {
            MatrixToImageWriter.writeToPath(bitMatrix, PNG_FORMAT, path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return fileName + "File write failed";
        }

        return "QR code generated: " + path.toString();
    }

    public static String readQR(String path)
    {
        BufferedImage bufferedImage = null;
        try
        {
            bufferedImage = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return path + " : File read failed";
        }
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try
        {
            return new MultiFormatReader().decode(bitmap, tmpHintsMap).getText();
        }
        catch (NotFoundException e)
        {
            e.printStackTrace();
            return "Message not found in image";
        }
    }
}
