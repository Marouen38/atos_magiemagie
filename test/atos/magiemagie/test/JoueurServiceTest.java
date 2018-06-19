/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.Joueur;
import atos.magiemagie.Service.JoueurService;
import atos.magiemagie.Partie;
import atos.magiemagie.Service.PartieService;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {

    private JoueurService service = new JoueurService();
    private PartieService partieService = new PartieService();

   // @Test
    public void ordreJoueurOK() {
        Partie nouvellePartie = partieService.creerNouvellePartie("ordreJoueurOK");

        service.rejoindrePartie("A", "A", nouvellePartie.getId());
        service.rejoindrePartie("B", "B", nouvellePartie.getId());
        Joueur j = service.rejoindrePartie("C", "C", nouvellePartie.getId());
        Assert.assertEquals(3L, j.getOdre());

    }

    @Test
    public void rejoindrePartieOK() {

        service.rejoindrePartie("thomas", "blabl", 1);
        service.rejoindrePartie("thomas2", "blabl", 1);
        service.rejoindrePartie("thomas3", "blabl", 1);

    }

}
