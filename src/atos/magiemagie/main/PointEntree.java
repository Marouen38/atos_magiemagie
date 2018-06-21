/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

import atos.magiemagie.Carte;
import atos.magiemagie.Joueur;
import atos.magiemagie.Partie;
import atos.magiemagie.Service.CarteService;
import atos.magiemagie.Service.JoueurService;
import atos.magiemagie.Service.PartieService;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class PointEntree {

    private PartieService partieService = new PartieService();
    private JoueurService joueurService = new JoueurService();
    private CarteService carteService = new CarteService();
    Scanner scan = new Scanner(System.in);

    public void menuPrincipale() {

        String choix;
        do {
            System.out.println("menu principale(");
            System.out.println("------------");
            System.out.println("1.lister les partiesnom demarrées");
            System.out.println("2.créer une partie");
            System.out.println("3.rejoindre parties");
            System.out.println("4.demmarer parties");
            System.out.println("Q.quitter");
            System.out.print("votre choix>");

            choix = scan.nextLine();

            switch (choix) {
                case "1":
                    List<Partie> parties = partieService.listerPartieNonDemarees();
                    System.out.println("-----------------");
                    System.out.println("Liste des parties");
                    for (Partie p : parties) {
                        System.out.println(p.getNom() + " " + p.getId());
                    }
                    System.out.println("-----------------");
                    break;
                case "2":
                    System.out.print("veuillez aficher le nom  de la pertie à creer:");
                    String nomLaPartie = scan.nextLine();
                    partieService.creerNouvellePartie(nomLaPartie);
                    break;
                case "3":
                    System.out.print("veuillez citer  la partie à rejoindre:");
                    long numLaPartie = Long.valueOf(scan.nextLine());
                    System.out.println("veuillez entrez votre pseudo");
                    String nomJoueur = scan.nextLine();
                    joueurService.rejoindrePartie(nomJoueur, "avatar", numLaPartie);

                    break;

                case "4":
                    System.out.println("veuillez afficher la partie à demmarer");
                    long numLaPartieADemmarer = Long.valueOf(scan.nextLine());
                    joueurService.demarerPartie(numLaPartieADemmarer);
                    jeuDemarre(numLaPartieADemmarer);
                    break;
                case "Q":
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix inconnue");

            }
        } while (!choix.equals("Q"));

        //Object nextElement = choix.nextElement();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PointEntree m = new PointEntree();
        m.menuPrincipale();

    }

    private void jeuDemarre(long idpartie) {
        while (true) {
            Joueur j = joueurService.joueurQuiALaMain(idpartie);
            List<Carte> cartes = carteService.listerCartesParJoueurId(j.getId());
            System.out.println("Les cartes de " + j.getPseudo());

            for (Carte c : cartes) {
                System.out.println(c.getTypeCarte());
            }
            System.out.println("");
            System.out.println("1.selectionner les cartes");
            System.out.println("2.passer son tour");
            String choix = scan.nextLine();
            switch (choix) {
                case "1":
                    System.out.println("quelle sort voulez vous lancer");
                    System.out.println("1.invisiblilité");
                    System.out.println("2.philtre d'amour");
                    System.out.println("3.hypnose");
                    System.out.println("4.divination");
                    System.out.println("5.sommeil-profond");
                    choix=scan.nextLine();
                    switch (choix) {
                        case"1":
                            //lancer un visibilité
                            if(joueurService.joueurALesCartes(j.getId(), Carte.TypeCarte.CORNE_LICORNE, Carte.TypeCarte.BAVE_CRAPAUD)){
                                System.out.println("Vous lancez invisibilité");
                                //on recupere la liste des autres joueurs
                                //on trouve une carte aleatoire pour chaque joueur
                                //le nouveau propietaire de toutes ces cartes c'est nous
                            } else {
                                System.out.println("Vous n'avez pas les cartes");
                            }
                            break;
                        case"2":
                             //lancer philtre d'amour
                            if(joueurService.joueurALesCartes(j.getId(), Carte.TypeCarte.CORNE_LICORNE, Carte.TypeCarte.MANDRAGORE)){
                                System.out.println("Vous lancez PHILTRE D’AMOUR");
                            } else {
                                System.out.println("Vous n'avez pas les cartes");
                            }
                           
                            break;
                         case"3":
                             //lancer hyponose
                             if(joueurService.joueurALesCartes(j.getId(), Carte.TypeCarte.LAPIS_LAZULI, Carte.TypeCarte.BAVE_CRAPAUD)){
                                System.out.println("Vous lancez HYPNOSE");
                            } else {
                                System.out.println("Vous n'avez pas les cartes");
                            }
                             break;
                         case"4":
                             //lancer divination
                             if(joueurService.joueurALesCartes(j.getId(), Carte.TypeCarte.LAPIS_LAZULI, Carte.TypeCarte.CHAUVE_SOURIS)){
                                System.out.println("Vous lancez DIVINATION");
                                for(Joueur j1 : partieService.autresJoueurs(j.getId())){
                                    System.out.println("Le joueur " + j1.getPseudo() + " a les cartes");
                                    for (Carte c: carteService.listerCartesParJoueurId(j1.getId())){
                                        System.out.println(c.getTypeCarte());   
                                    }
                                }
                            } else {
                                System.out.println("Vous n'avez pas les cartes");
                            }
                             break;
                          case"5":
                              //sommeil profond
                              if(joueurService.joueurALesCartes(j.getId(), Carte.TypeCarte.MANDRAGORE, Carte.TypeCarte.CHAUVE_SOURIS)){
                                System.out.println("Vous lancez SOMMEIL-PROFOND");
                            } else {
                                System.out.println("Vous n'avez pas les cartes");
                            }
                        
                    }
                    break;
                case "2":
                            joueurService.piocherCarte(j.getId());
                            break;

                    }
                    System.out.println("");
                    partieService.passeJoueurSuivant(idpartie);
                //passer au joueur suivant
            }
        }

    }
