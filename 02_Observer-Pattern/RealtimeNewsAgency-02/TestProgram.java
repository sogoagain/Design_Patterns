/**
 * Created by sogoesagain on 2017. 3. 23..
 */
public class TestProgram {
    public static void main(String[] args) {
        NewsPublisher newsServer = new BreakingNewsPublisher();
        NewsClient client1 = new NewsClient("yongmin");
        NewsClient client2 = new NewsClient("mina");
        client1.setInterval(1);
        client2.setInterval(3);
        newsServer.addObserver(client1);
        newsServer.addObserver(client2);

        newsServer.setNewFeed("1. 서울 삼성, 6강 플레이오프 예매 오픈 실시");
        newsServer.setNewFeed("2. ‘앞뒤 안보고 뛰어들어’…영웅으로 떠오른 영국 외무차관");
        newsServer.setNewFeed("3. 만화 등 주요 콘텐츠 6개 분야에 300억 투입");
        newsServer.setNewFeed("4. ‘유러피언 드림’vs ‘프랑스 우선주의’ 대격돌");

        newsServer.deleteObserver(client1);
        client2.setInterval(2);

        newsServer.setNewFeed("5. 기업분할로 핵심사업 집중종합중공업그룹 ‘날갯짓’");

        NewsClient client3 = new NewsClient("cacao");
        client3.setInterval(4);
        newsServer.addObserver(client3);

        newsServer.setNewFeed("6. 탄산음료·튀김·아이스크림…알고보니 ‘세계 10대 불량식품’!");
        newsServer.setNewFeed("7. JTBC GOLF, 24시간 LPGA 전용 디지털 채널 개국");
        newsServer.setNewFeed("8. LG G6 적용된 돌비기술 체험해보니....사람 눈으로 본 듯 생생");
        newsServer.setNewFeed("9. 식품업계 브라질산 닭고기 퇴출 잇따라");
        newsServer.setNewFeed("10. 적당히 달고 말랑한 식감 “젤리 즐기는 어른 는다”");
        newsServer.setNewFeed("11. 게임위, 기관 마스코트 ‘와치’와 ‘캐치’ 공개");


    }
}
