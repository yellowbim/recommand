package jjuni.pjt.recommend.common.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParsing {

    // xml 값을 받아서 파싱 => List<Map<String,String>> 형식으로 반환
    public List<Map<String,Object>> xmlParsing(Document document, String tagId) throws IOException {
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
}
