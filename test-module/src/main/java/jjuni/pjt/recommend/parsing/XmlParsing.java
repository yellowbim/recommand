package jjuni.pjt.recommend.parsing;

import jjuni.pjt.recommend.parsing.VO.RessvrVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class XmlParsing {

    private String serverCheck = "http://rcs.xroshot.com/catalogs/MAS/recommended/0";
    private String uploadServerCheck = "http://rcs.xroshot.com/catalogs/FUS-TCSMSG/recommended/0";

    /**
     * 1-1. api 요청/수신 후 xml 처리
     * HTTP 통신
     * @param null
     * @response xml
     * ********************************************************************************************************************************************************************************************************************
     */
    @RequestMapping("/resParsing")
    @ResponseBody
    public List<Map<String,Object>> resParsing(@RequestParam(value = "tagId", defaultValue = "RCP") String tagId, @RequestParam(value = "surl") String surl) throws IOException, ParserConfigurationException, SAXException {
        List<Map<String,Object>> parsingResult;

        if ("file".equals(surl)) { // 특정 파일로 작업
            File file = new File("D:\\99.etc\\98.private\\03.test\\test\\src\\main\\java\\com\\example\\test\\parsing\\testXML.xml");
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = (Document) docBuilder.parse(new InputSource(String.valueOf(file)));
            // document를 xml 형식으로 변경해주는 작업
            document.getDocumentElement().normalize();

            // 3. xml 파싱
            parsingResult = xmlParsing(document, tagId);

        } else { // url인경우
            URL url = null;
            if ("server".equals(surl)) {
                url = new URL(serverCheck);
            } else {
                url = new URL(uploadServerCheck);
            }
            // 1. 접속정보 셋팅
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestMethod("GET");
            conn.connect();

            // 2. xml 값 수신
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = (Document) docBuilder.parse(new InputSource(conn.getInputStream()));
            // document를 xml 형식으로 변경해주는 작업
            document.getDocumentElement().normalize();

            // 3. xml 파싱
            parsingResult = xmlParsing(document, tagId);
        }

        return parsingResult;
    }

    // xml 값을 받아서 파싱 => List<Map<String,String>> 형식으로 반환
    private List<Map<String,Object>> xmlParsing(Document document, String tagId) throws IOException {
        List<Map<String,Object>> rList = new ArrayList<>();

        // 부모노드면 아래쪽 탈 필요가 없음(속성만 return)
        if (tagId.equals(document.getDocumentElement().getTagName())) {
            Map<String,Object> map = new HashMap<>();
            map.put(document.getDocumentElement().getAttributes().getNamedItem("method").getNodeName(), document.getDocumentElement().getAttributes().getNamedItem("method").getTextContent());
            rList.add(map);
            return rList;
        }

        // 하위 노드 값 구하기
        NodeList nList = document.getElementsByTagName(tagId);

        // for문으로 각 태그 key, value 가져오기
        for (int i=0; i < nList.getLength(); i++) {
            Node item = nList.item(i);

            Element eElement = (Element) item;

            // 태그들 사이에 공백으로 구분이 되기때문에 판단을 해줘야함
            if(item.getNodeType() == Node.ELEMENT_NODE){
                rList.add(childTagParsing(eElement));
            }
        }

        System.out.println(rList.toString());

        return rList;
    }

    // 자식태그 파싱 => 결과값 Map<String, String>
    private Map<String, Object> childTagParsing(Node node) {
        Map<String, Object> map = new HashMap<>(); // 결과값을 담을 map

        if (node.getChildNodes().getLength() > 1) {
            for (int i=0; i < node.getChildNodes().getLength(); i++) {
                if(node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE){
                    if (node.getChildNodes().item(i).getChildNodes().getLength() > 1) {
                        map.put(node.getChildNodes().item(i).getNodeName(), childTagParsing(node.getChildNodes().item(i)));
                    } else {
                        map.put(node.getChildNodes().item(i).getNodeName(), node.getChildNodes().item(i).getTextContent());
                    }
                }
            }
        } else {
            map.put(node.getNodeName(), node.getTextContent());
        }

        return map;
    }

    /**
     * 1-2. api 요청/수신 후 xml 처리
     * HTTP 통신(POST)
     * - 파일 업로드 통신규격
     * @method PUT/POST
     * @param xml
     * @response xml
     * ********************************************************************************************************************************************************************************************************************
     */
    @RequestMapping("/resParsing1")
    @ResponseBody
    public List<Map<String,Object>> resParsing1(@RequestParam(value = "tagId", defaultValue = "RCP") String tagId) throws IOException, ParserConfigurationException, SAXException {
        // 1. 접속정보 셋팅
        String test1Url = "http://localhost:82/httpParsing";

        // 2. 접속 파라미터 셋팅
        URL url = new URL(test1Url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("HOST", "222.222.222.222");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("X-FUS-Authentication", "발급받은 권한 정보를 셋팅");
        conn.setDoOutput(true);
        conn.connect();

        // 2. xml 값 수신
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = (Document) docBuilder.parse(new InputSource(conn.getInputStream()));
        document.getDocumentElement().normalize();

        // 3. xml 파싱
        List<Map<String,Object>> parsingResult = xmlParsing(document, tagId);

        return parsingResult;
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
     * 2. JAXB를 사용한 Marshalling, Unmarshalling
     * ********************************************************************************************************************************************************************************************************************
     */
    @RequestMapping("/resParsing2")
    @ResponseBody
    public Map resParsing2(@RequestParam(value = "surl") String surl) throws Exception {
        Map<String, RessvrVO> map = new HashMap<>();
//        RessvrVO ressvrVO = new RessvrVO();


        if ("file".equals(surl)) { // 특정 파일로 작업
            // Unmarshalling
            File file = new File("D:\\99.etc\\98.private\\03.test\\test\\src\\main\\java\\com\\example\\test\\parsing\\testXML.xml");
            // 파일이라서 String 변환하는 작업이 필요
            String files = readFileAsString(file.toString());
            System.out.println("files = " + files);

            JAXBContext jaxbContext = JAXBContext.newInstance(RessvrVO.class); // JAXB Context 생성
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); // Unmarshaller Object 생성
            RessvrVO ressvrVO = (RessvrVO) unmarshaller.unmarshal(new StringReader(files)); // unmarshall 메소드 호출

            map.put("ressvrVO", ressvrVO);
            System.out.println("ressvrVO : " + ressvrVO);


            RessvrVO ressvrVO1 = new RessvrVO();
            RessvrVO.Resources resources = new RessvrVO.Resources();
            resources.setCategory("ttttttt");
            resources.setResourceID("qqqqqqqqqqqqq");
            resources.setAddress("111.111.111.111");
            resources.setPort("9999");


            ressvrVO1.setMethod("test");
            StringWriter sw = new StringWriter();

            ressvrVO1.setResources(resources);
            // Marshalling
//            JAXBContext jaxbContext = JAXBContext.newInstance(RessvrVO.class); // JAXB Context 생성
            Marshaller marshaller = jaxbContext.createMarshaller(); // marshaller Object 생성
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(ressvrVO1, System.out);
            marshaller.marshal(ressvrVO1, sw);

            String result = sw.toString();
            System.out.println("result = " + result);

            



            return map;


        } else { // url인경우
//            URL url = null;
//            if ("server".equals(surl)) {
//                url = new URL(serverCheck);
//            } else {
//                url = new URL(uploadServerCheck);
//            }
//            // 1. 접속정보 셋팅
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestProperty("Content-Type", "application/xml");
//            conn.setRequestMethod("GET");
//            conn.connect();
//
//            // 2. xml 값 수신
//            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//            Document document = (Document) docBuilder.parse(new InputSource(conn.getInputStream()));
//            // document를 xml 형식으로 변경해주는 작업
//            document.getDocumentElement().normalize();
//
//            // 3. xml 파싱
//            parsingResult = xmlParsing(document, tagId);
        }

        return map;
    }
    
    
    
    // 파일 string 변환
    public String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
