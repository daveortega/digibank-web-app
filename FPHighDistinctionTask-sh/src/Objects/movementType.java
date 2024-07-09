/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Juan David
 */
public class movementType {
    
    private String MovType;
    private String MovDescr;

    public String getMovType() {
        return MovType;
    }

    public void setMovType(String MovType) {
        this.MovType = MovType;
    }

    public String getMovDescr() {
        return MovDescr;
    }

    public void setMovDescr(String MovDescr) {
        this.MovDescr = MovDescr;
    }
    
    @Override
    public String toString() {
        return MovDescr;
    }
}
