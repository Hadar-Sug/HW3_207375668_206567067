public enum ScanningType {
    ALL(0),
    COMPLETED(1),
    UNCOMPLETED(2);

    private final int val;

    ScanningType(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
