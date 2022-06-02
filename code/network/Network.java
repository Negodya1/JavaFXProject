package code.network;

public class Network {
    private Socketwork work;

    public Network() {
        //work = new Socketwork("62.33.8.140",5001);
        work = new Socketwork("127.0.0.7",5001);
    }

    public boolean send(int sended) {
        return work.send(sended);
    }

    public boolean send(double sended) {
        return work.send(sended);
    }

    public boolean send(String sended) {
        return work.send(sended);
    }

    public int waitForInt() {
        return work.waitForInt();
    }

    public double waitForDouble() {
        return work.waitForDouble();
    }

    public String waitForString() {
        return work.waitForString();
    }
}