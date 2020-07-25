package com.myqrreader.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Barcode reader application
 *
 */
public class App 
{
    private static void printUsage()
    {
        String message = "QR Code Application\n"
             + "Usage: java -jar qrcode.jar message filename[default=qr_code.jpg]";
        System.out.println(message);
    }
    public static void main( String[] args)
    {
        if (args == null || args.length < 1)
        {
            printUsage();
            System.exit(1);
        }
        String message = args[0];
        String fileName = args.length < 3 ? args[1]: "qr_code.jpg";

        System.out.println( "QR Application!" );
        try
        {
            System.out.println(QRCode.createQR(message, fileName));
            // System.out.println("QR message: " + QRCode.readQR(fileName));
        }
        catch (Exception e)
        {
            System.out.println("Failed with exception" +  e);
            e.printStackTrace();
        }
    }

//    static CommandLine getCommandLine(String[] args) throws ParseException
//    {
//        CommandLineParser parser = new DefaultParser();
//        try {
//            // parse the command line arguments
//            return parser.parse(createOptions(), args);
//
//        }
//        catch (ParseException exp) {
//            // oops, something went wrong
//            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
//            throw exp;
//        }
//    }
//
//    static Options createOptions() {
//
//        Options options = new Options();
//
//        // automatically generate the help statement
//        HelpFormatter formatter = new HelpFormatter();
//        formatter.printHelp( "ant", options );
//        Option help = new Option( "help", "QR code application");
//
//        // file name
//        Option saveLocation   = OptionBuilder.withArgName( "location" )
//            .hasArg()
//            .withDescription(  "use given file for qr code image" )
//            .create( "saveLocation" );
//
//        Option bill  = OptionBuilder.withArgName( "bill" )
//            .hasArg()
//            .withDescription("Bill No ")
//            .create( "bill" );
//
//        Option upiId  = OptionBuilder.withArgName( "upi" )
//            .hasArg()
//            .withDescription("UPI ID")
//            .create( "upi" );
//
//        Option amount  = OptionBuilder.withArgName( "amount" )
//            .hasArg()
//            .withDescription("Amount to pay ")
//            .create( "amount" );
//
//        Option fileName  = OptionBuilder.withArgName( "filename" )
//            .hasArg()
//            .withDescription("File name of qr code image")
//            .create( "filename" );
//
//
//        options.addOption(help);
//        options.addOption(saveLocation);
//        options.addOption(bill);
//        options.addOption(upiId);
//        options.addOption(amount);
//        options.addOption(fileName);
//
//        return options;
//    }
}
