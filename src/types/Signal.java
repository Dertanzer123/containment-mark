package types;

public class Signal {
    public SignalCode signalCode;
    public Object signalData;

    public Signal(SignalCode signalCode, Object signalData) {
        this.signalCode = signalCode;
        this.signalData = signalData;
    }
}
