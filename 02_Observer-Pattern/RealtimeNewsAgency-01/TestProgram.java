/**
 * Created by sogoagain on 2017. 3. 23..
 */
public class TestProgram {
    public static void main(String[] args) {
        NewsPublisher sportsNewsServer = new SportsNewsPublisher();
        NewsPublisher itNewsServer = new ITNewsPublisher();
        NewsClient client1 = new NewsClient("yongmin");
        NewsClient client2 = new NewsClient("mina");
        sportsNewsServer.addObserver(client1);
        sportsNewsServer.addObserver(client2);
        itNewsServer.addObserver(client1);
        itNewsServer.addObserver(client2);

        sportsNewsServer.setNewFeed("리버풀 1: 맨유 0");
        sportsNewsServer.setNewFeed("리버풀 2: 맨유 0");
        sportsNewsServer.deleteObserver(client2);
        sportsNewsServer.setNewFeed("리버풀 2: 맨유 1");
        sportsNewsServer.setNewFeed("리버풀 3: 맨유 1");

        itNewsServer.setNewFeed("애플의 빨간 아이폰 '습격'일까 '항복'일까");
        itNewsServer.setNewFeed("글로벌 기업 250곳 \"구글 광고 보이콧\"");
        itNewsServer.deleteObserver(client1);
        itNewsServer.setNewFeed("갤럭시탭S3 LTE, 자급제로 간다");
    }
}
