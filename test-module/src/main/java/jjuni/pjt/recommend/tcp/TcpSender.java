package jjuni.pjt.recommend.tcp;

import jjuni.pjt.recommend.common.util.XmlParsing;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TcpSender {


    /**
     * tcp 전송 **************************************************************************
     */

    public void send() {

        Socket socket = null;

        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;

        InputStream inputStream = null;
        DataInputStream dataInputStream = null;

        Scanner sc = new Scanner(System.in);

        try {
            // 1. 소켓 연결
            socket = new Socket("localhost", 7000);
            System.out.println("서버 연결 완료!");

            // 2. 전송 stream 객체 생성
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

            // 3. 수신 stream 객체 생성
//            inputStream = socket.getInputStream();
//            dataInputStream = new DataInputStream(inputStream);
            byte[] recvBuffer = new byte[1024];
            InputStream is = socket.getInputStream();

            // 5. 수신 문자열
            String inputMessage = null;

            // 6. 파일 읽어서 한번 전송
            File file = new File("D:\\99.etc\\98.private\\03.test\\test1\\src\\main\\java\\com\\example\\test\\tcp\\reqServerTime.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = (Document) documentBuilder.parse(new InputSource(String.valueOf(file)));
            document.getDocumentElement().normalize();

            String stringDoc = documentToString(document);
            System.out.println("전송 데이터\n" + stringDoc);

            // 7. 데이터 전송
            dataOutputStream.write(stringDoc.getBytes());
            dataOutputStream.flush(); // close 하는곳에서 flush를 해주지만 혹시 모르니 해주는게 맞음

            // 7. 데이터 수신 대기
            int nReadLength = is.read(recvBuffer);
            inputMessage = new String(recvBuffer, 0, nReadLength);
            System.out.println("수신 데이터\n" + inputMessage);

            // 8. 수신 데이터 변환
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            document = (Document) docBuilder.parse(new InputSource(new StringReader(inputMessage)));
            // document를 xml 형식으로 변경해주는 작업
            document.getDocumentElement().normalize();
            XmlParsing xml = new XmlParsing();
            List<Map<String,Object>> parsingResult = xml.xmlParsing(document, "Path");
            System.out.println("parsingResult = " + parsingResult);


            createXML();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (dataOutputStream != null) dataOutputStream.close();
                if (outputStream != null) outputStream.close();
                if (dataInputStream != null) dataInputStream.close();
                if (inputStream != null) inputStream.close();
                System.out.println("소켓 종료!");
                socket.close();
                System.out.println("\n\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public String documentToString(Document doc) throws TransformerException {
        StringWriter clsOutput = new StringWriter( );
        Transformer clsTrans = TransformerFactory.newInstance( ).newTransformer( );

        clsTrans.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "no" );
        clsTrans.setOutputProperty( OutputKeys.METHOD, "xml" );
        clsTrans.setOutputProperty( OutputKeys.INDENT, "yes" );
        clsTrans.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );

        clsTrans.transform( new DOMSource( doc ), new StreamResult( clsOutput ) );

        return clsOutput.toString( );
    }

    /**
     * xml 형식 생성
     * - 내가 만든 방식으로 xml 구조 만들면 될듯
     * - tcp 통신 시 데이터를 전달하기 위한 xml 생성
     */
    private void createXML() throws ParserConfigurationException, TransformerException, FileNotFoundException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.newDocument();
        doc.setXmlStandalone(true);

        // root 태그
        Element mas = doc.createElement("MAS");
        doc.appendChild(mas);
        mas.setAttribute("method","req_storage");

        // root 자식태그
        Element filename = doc.createElement("filename");
        filename.appendChild(doc.createTextNode("513318114422640_conv.tif"));
        mas.appendChild(filename);

        // 새로운 태그는 doc.createElement()
        // 가장 바깥쪽부터 샇아올라감
        Element customMessageId = doc.createElement("CustomMessageID");
        customMessageId.appendChild(doc.createTextNode("11178"));
        mas.appendChild(customMessageId);

        // 메시지 태그 만들고 내부에 태그 append 하고 마지막에 추가해줘야함
        Element message = doc.createElement("Message");

        Element receiveNumber = doc.createElement("ReceiveNumber");
        receiveNumber.appendChild(doc.createTextNode("YyxNJnuT420IUjHhPcyatw=="));
        receiveNumber.setAttribute("seqNo","1");
        message.appendChild(receiveNumber);

        Element subject = doc.createElement("Subject");
        subject.appendChild(doc.createTextNode("메시지 줴목"));
        message.appendChild(subject);

        Element attachment = doc.createElement("Attachment");
        attachment.appendChild(doc.createTextNode("ifLJBS/GsM2YGXmELAPuFfDgEb5YjWaQldR799+hhSHuQQxaQ1+2evBpPeQLYpdY6qkcKT6TYmqZVr7Bi2edIKebggN3tvZ0lI+2y/csJnP/4sLZ9odYVAltZC8ubO7SYUG1Cxq1q5KWbrYcJMYmBGT/RyntYK5teVLEYf9qv0Pxx6S9BUxmzEtaSkonL3h4aj7TWgOSJnVBfa1Jf6Fqi61do+gM0ES 3xHDXqrB3JkFcTwkYQfc0FQIqBrLn9XAxbwcQKiQOylzHjZn7RZjlqw=="));
        attachment.setAttribute("attachID","1");
        message.appendChild(attachment);

        Element fileSize = doc.createElement("FileSize");
        fileSize.appendChild(doc.createTextNode("123456"));
        message.appendChild(fileSize);

        Element pageCount = doc.createElement("PageCount");
        pageCount.appendChild(doc.createTextNode("1"));
        message.appendChild(pageCount);

        mas.appendChild(message);






        // XML 파일로 쓰기
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //정렬 스페이스4칸
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //들여쓰기
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); 했을때 붙어서 출력되는부분 개행

        String test= documentToString(doc);
        System.out.println("test = " + test);

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(new File("D://test.xml")));

        transformer.transform(source, result);


    }

}
