/**
 * Created by sogoesagain on 2017. 3. 16..
 */
public enum HandType {
    // 상수("연결할 문자")
    GAWI("가위"), BAWI("바위"), BO("보");

    final private String handName;

    //enum에서 생성자 같은 역할
    private HandType(String handName) {
        this.handName = handName;
    }

    @Override
    public String toString() { // 문자를 받아오는 함수
        return handName;
    }

    public static HandType valueOf(int n) {
        return HandType.values()[n];
    }

    public HandType winValueOf() {
        return HandType.valueOf((this.ordinal() + 1) % 3);
    }
}
