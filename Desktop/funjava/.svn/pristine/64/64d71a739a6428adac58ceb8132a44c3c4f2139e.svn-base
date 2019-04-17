package org.hphc.poc.passkit.function.service;

import java.awt.Color;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import de.brendamour.jpasskit.*;
import de.brendamour.jpasskit.PKPass;
import de.brendamour.jpasskit.passes.*;
import de.brendamour.jpasskit.signing.PKFileBasedSigningUtil;
import de.brendamour.jpasskit.signing.PKPassTemplateFolder;
import de.brendamour.jpasskit.signing.PKSigningInformation;
import de.brendamour.jpasskit.signing.PKSigningInformationUtil;
import org.hphc.poc.passkit.function.pojo.HPHCPassField;
import org.hphc.poc.passkit.function.pojo.Pass;


public class PasskitService {
    public static final String PASS_TYPE_IDENTIFIER = "pass.org.hphc.idcard";
    public static final String APPLEPASSDOCTYPE= "applePass";

    public static byte[] getPassBytes(Pass passEntity, Logger logger) throws Exception {

        String dirPath="/home/stuff/applecerts/";
        PKPass pass=getPass(passEntity);
        if (!pass.isValid())
        {
            logger.warning("pass.getValidationErrors() = " + pass.getValidationErrors());
        }
        PKSigningInformation pkSigningInformation = new PKSigningInformationUtil().loadSigningInformationFromPKCS12AndIntermediateCertificate(dirPath + "private.p12",  "password", dirPath+ "Apple.pem");
        PKPassTemplateFolder passTemplate = new PKPassTemplateFolder(dirPath +"HPHC.pass");
        PKFileBasedSigningUtil pkSigningUtil = new PKFileBasedSigningUtil();
        byte[] signedAndZippedPkPassArchive = pkSigningUtil.createSignedAndZippedPkPassArchive(pass, passTemplate, pkSigningInformation);
        return signedAndZippedPkPassArchive;
        //FileUtils.writeByteArrayToFile(new File("/stuff/applecerts/new.pkpass"),signedAndZippedPkPassArchive);
    }

	
    private static PKPass  getPass(Pass passEntity) throws Exception{
        PKPass pass = new PKPass();
        //PKBarcode barcode = new PKBarcode();


        PKEventTicket storeCard = new PKEventTicket() ;
        List<PKField> primaryFields = new ArrayList<PKField>();
        List<PKField> secondaryFields = new ArrayList<PKField>();
        List<PKField> backFields = new ArrayList<PKField>();
        List<PKField> auxFields = new ArrayList<PKField>();
        List<PKField> headerFields = new ArrayList<PKField>();


        for (HPHCPassField field: passEntity.getPassFields()){
            PKField iDCardField = new PKField();
            iDCardField.setChangeMessage(field.getChangeMessage());
            iDCardField.setValue(field.getValue());
            iDCardField.setKey(field.getKey());
            iDCardField.setLabel(field.getLabel());
            // primary, secondary,back,aux, header
            if ("primary".equalsIgnoreCase( field.getFieldLocation()))
            {
                primaryFields.add(iDCardField);
            }
            else
            if ("secondary".equalsIgnoreCase( field.getFieldLocation()))
            {
                secondaryFields.add(iDCardField);
            }
            if ("back".equalsIgnoreCase( field.getFieldLocation()))
            {
                backFields.add(iDCardField);
            }
            if ("aux".equalsIgnoreCase( field.getFieldLocation()))
            {
                auxFields.add(iDCardField);
            }
            if ("header".equalsIgnoreCase( field.getFieldLocation()))
            {
                headerFields.add(iDCardField);
            }

        }

        
        storeCard.setPrimaryFields( primaryFields );
        storeCard.setSecondaryFields(secondaryFields);
        storeCard.setBackFields(backFields);
        storeCard.setAuxiliaryFields(auxFields);
        storeCard.setHeaderFields(headerFields);

        pass.setFormatVersion( 1 );
        //******

       //HPHCPassConfig hphcPassConfig= passConfigService.getHPHCPassConfig();
        pass.setSerialNumber((passEntity!=null)?passEntity.getSerialNumber():"000000001");

        pass.setPassTypeIdentifier(PASS_TYPE_IDENTIFIER);
        pass.setTeamIdentifier( "QP4Z47X9QT" );
        //pass.setBarcode( barcode );
        pass.setOrganizationName( "Harvard Pilgrim Org" );
        //pass.setLogoText( "Harvard Pilgrim Healthcare" );
        pass.setDescription( "ID Card" );
        pass.setBackgroundColorAsObject( Color.WHITE);
        pass.setForegroundColor( "rgb(0,0,0)" );

        //https://poc-hphc-passkit.azure-api.net
        //pass.setWebServiceURL(new URL("https://jpasskit-190112173729.azurewebsites.net/jpasskit-server2-1.0-SNAPSHOT/svc"));
        pass.setWebServiceURL(new URL("https://digitalid.azurewebsites.net/api/applepass"));
         
        
        //pass.setWebServiceURL(new URL(hphcPassConfig.getWebServiceURLString()));


        pass.setEventTicket(storeCard);
        //pass.setAuthenticationToken("token1234567890token1234567890token1234567890");
        pass.setAuthenticationToken((passEntity!=null)?passEntity.getAuthenticationToken():"token1234567890token1234567890token1234567890");

        //pass.setAppLaunchURL("https://www.harvardpilgrim.org/hphconnect/images/card.jpg");
        
        if (!pass.isValid())
        {
            System.out.println("pass.getValidationErrors() = " + pass.getValidationErrors());
        }

        return pass;
    }


    /*{
            PKField iDCardField = new PKField();

            iDCardField.setKey("name");
            iDCardField.setLabel("Name");
            iDCardField.setValue((passEntity!=null)?passEntity.getPersonName():"John Q Sample");
            iDCardField.setChangeMessage("Your name has changed to %@ on your Harvard Pilgrim ID Card");
            primaryFields.add(iDCardField);
        }*/
        /*{
            PKField iDCardField = new PKField();
            iDCardField.setKey("hpNumber");
            iDCardField.setLabel("ID Number");
            iDCardField.setValue((passEntity!=null)?passEntity.getMemberNumber():"HP123456789");
            iDCardField.setChangeMessage("Your ID Number has changed to %@ on your Harvard Pilgrim ID Card");
            secondaryFields.add(iDCardField);
        }*/
        /*{
            PKField iDCardField = new PKField();
            iDCardField.setKey("division");
            iDCardField.setLabel("Group#");
            iDCardField.setValue((passEntity!=null)?passEntity.getGroupNumber():"999990000");
            secondaryFields.add(iDCardField);
        }*/
        /*{
            PKField iDCardField = new PKField();
            iDCardField.setKey("aux1");
            iDCardField.setLabel("Co-Pay");
            iDCardField.setValue((passEntity!=null)?passEntity.getCoPay():"$0 Select Preventive Service Only");
            auxFields.add(iDCardField);
        }*/
        /*{
            PKField iDCardField = new PKField();
            iDCardField.setKey("aux2");
            iDCardField.setLabel("Some-Info");
            iDCardField.setValue("Some More information");
            auxFields.add(iDCardField);
        }
        {
            PKField iDCardField = new PKField();
            iDCardField.setKey("longText1");
            iDCardField.setLabel("Some Long Text 1");
            iDCardField.setValue("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
            auxFields.add(iDCardField);
        }
        {
            PKField iDCardField = new PKField();
            iDCardField.setKey("website");
            iDCardField.setLabel("ID Card");
            iDCardField.setValue("https://www.harvardpilgrim.org/hphconnect/images/card.jpg");
            backFields.add(iDCardField);
        }
        {
            PKField iDCardField = new PKField();
            iDCardField.setKey("longTextBack1");
            iDCardField.setLabel("Some Back Text 1");
            iDCardField.setValue("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
            backFields.add(iDCardField);
        }

        {
            PKField iDCardField = new PKField();
            iDCardField.setKey("custsrv");
            iDCardField.setLabel("Member Services");
            iDCardField.setValue("(800) 555-5555");
            backFields.add(iDCardField);
        }*/


        /*List<PKField> headerFields= new ArrayList<PKField>();
        {
            PKField iDCardField = new PKField();
            iDCardField.setKey("hpNumber");
            iDCardField.setLabel("GroupID Number");
            iDCardField.setValue("HP12345678");
            headerFields.add(iDCardField);
        }*/
        //iDCardField.set.setCurrencyCode( "EUR" );



        /*barcode.setFormat( PKBarcodeFormat.PKBarcodeFormatQR );
        barcode.setMessage( "ABCDEFG" );
        barcode.setMessageEncoding( Charset.forName( "utf-8" ) );
*/
}