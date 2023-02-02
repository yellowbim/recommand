package jjuni.pjt.recommend.tcp;

import jjuni.pjt.recommend.common.util.XmlParsing;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

//@Controller
public class TcpListener {

    /**
     * tcp 전송 **************************************************************************
     */

    public void receive() {
        while(true) {
            listenTCP();
        }
    }

    public void listenTCP() {
        System.out.println("리스너 작동!!");
        ServerSocket serverSocket = null;
        Socket socket = null;

        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;

        InputStream inputStream = null;
        DataInputStream dataInputStream = null;

        try {
            // 1. 소켓 연결
            serverSocket = new ServerSocket(7000);
            System.out.println("클라이언트로부터 데이터 전송받을 준비 완료");

            // 2. 클라이언트 서버 소켓 허용
            socket = serverSocket.accept();
            System.out.println("클라이언트 연결 완료");
            System.out.println("socket : " + socket);

            // 3. 소캣 데이터 수신 stream 객체 생성
            byte[] recvBuffer = new byte[1024];
            InputStream is = socket.getInputStream();
//            inputStream = socket.getInputStream();
//            dataInputStream = new DataInputStream(inputStream);

            // 4. 소캣 데이터 전송 stream 객체 생성
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

            // 5. 수신 문자열
            String inputMessage = null;

            // 6. 데이터 수신
            int nReadLength = is.read(recvBuffer);
            System.out.println("데이터 수신 길이 " + nReadLength);
            inputMessage = new String(recvBuffer, 0, nReadLength);
            System.out.println("수신 데이터 : \n" + inputMessage);

            // 7. 수신 완료 데이터 전송(샘플 파일)
            // 파일 데이터를 문자열로 변환
            File file = new File("D:\\99.etc\\98.private\\03.test\\test\\src\\main\\java\\com\\example\\test\\tcp\\fileUpload.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = (Document) documentBuilder.parse(new InputSource(String.valueOf(file)));
            document.getDocumentElement().normalize();
            String stringDoc = documentToString(document);
            dataOutputStream.write(stringDoc.getBytes());
            dataOutputStream.flush(); // close 하는곳에서 flush를 해주지만 혹시 모르니 해주는게 맞음
            System.out.println("전송 데이터 \n" + stringDoc);

            // 8. xml 파싱
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            document = (Document) docBuilder.parse(new InputSource(new StringReader(inputMessage)));
            // document를 xml 형식으로 변경해주는 작업
            document.getDocumentElement().normalize();

            XmlParsing xmlParsing = new XmlParsing();
            List<Map<String,Object>> parsingResult = xmlParsing.xmlParsing(document, "Time");
            System.out.println("parsingResult = " + parsingResult);

        } catch (Exception e) {
            e.getMessage();
        } finally {

            try {

                if (dataOutputStream != null) dataOutputStream.close();
                if (outputStream != null) outputStream.close();
                if (dataInputStream != null) dataInputStream.close();
                if (inputStream != null) inputStream.close();
                System.out.println("소켓 종료!");
                socket.close();
                System.out.println("서버 소켓 종료!"); // 서버는 소켓을 닫아줘야함
                serverSocket.close();
                System.out.println("\n\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * file to string
     * @param doc
     * @return
     * @throws TransformerException
     */
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
}

