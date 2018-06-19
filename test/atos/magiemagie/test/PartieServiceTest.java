/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.Service.JoueurService;
import atos.magiemagie.Partie;
import atos.magiemagie.Service.PartieService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class PartieServiceTest {

    private JoueurService serviceJ = new JoueurService();
    private PartieService service = new PartieService();

    //@Test
    public void creerNouvellePartieOK() {
        Partie p = service.creerNouvellePartie("balaba");
        assertNotNull(p.getId());
    }

    @Test
    public void demarerPartie() {

        serviceJ.demarerPartie(1);
    }

}
