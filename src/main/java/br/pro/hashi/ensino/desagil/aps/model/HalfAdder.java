package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nandLeft;
    private final NandGate nandRight;
    private final NandGate nandTop;
    private final NandGate nandBottom;




    public HalfAdder() {
        super("HalfAdder", 2, 2);

        nandLeft = new NandGate();
        nandRight = new NandGate();
        nandTop = new NandGate();
        nandBottom = new NandGate();
        nand1 = new NandGate();
        nand2 = new NandGate();




        nandTop.connect(1, nandLeft);


        nandBottom.connect(0, nandLeft);


        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);

        nand2.connect(0, nand1);
        nand2.connect(1, nand1);

    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin < 0 || outputPin > 1) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        if (outputPin == 0) {
            return nandRight.read();
        }

        if (outputPin == 1) {
            return nand2.read();
        }

        return false;

    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                nand1.connect(0,emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                nand1.connect(1,emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}

