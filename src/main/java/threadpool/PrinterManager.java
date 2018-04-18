/*
package threadpool;

import com.sun.org.apache.xml.internal.serialize.Printer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

*/
/**
 * Created by xingfenhao on 2017/3/14.
 *//*


public class PrinterManager {
    private final Semaphore semaphore;
    private final List<Printer> printers = new ArrayList<Printer>();

    public PrinterManager(Collection<? extends Printer> printers) {
        this.printers.addAll(printers);
        //这里重载方法，第二个参数为true，以公平竞争模式，防止线程饥饿
        this.semaphore = new Semaphore(this.printers.size(), true);
    }

    public Printer acquirePrinter() throws InterruptedException {
        semaphore.acquire();
        return getAvailablePrinter();
    }

    public void releasePrinter(Printer printer) {
        putBackPrinter(printer);
        semaphore.release();
    }

    private synchronized Printer getAvailablePrinter() {
        Printer result = printers.get(0);
        printers.remove(0);
        return result;
    }

    private synchronized void putBackPrinter(Printer printer) {
        printers.add(printer);
    }
}
*/
