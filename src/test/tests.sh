fichier="temp_test.txt"

#get competitions 
echo -e "\n ### test get competitions ### \n"
curl localhost:8080/competitions > $fichier
diff $fichier competitions.txt  > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#get equipes
echo -e "\n ### test get equipes ### \n"
curl localhost:8080/equipes > $fichier
diff $fichier equipes.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#get matches
echo -e "\n ### test get matches ### \n"
curl localhost:8080/matches > $fichier
diff $fichier matches.txt  > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#get equipes/{id}
echo -e "\n ### test récupération d'une équipe ### \n"
curl localhost:8080/equipes/5 > $fichier
diff $fichier equipe_5.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#get competitions/{id}
echo -e "\n ### test récupération d'une competition ### \n"
curl localhost:8080/competitions/3 > $fichier
diff $fichier compet_3.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#get matches/{id}
echo -e "\n ### test récupération d'un match ### \n"
curl localhost:8080/matches/4 > $fichier
diff $fichier match_4.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#post equipe 
echo -e "\n ### test rajouter une équipe ### \n"
curl localhost:8080/equipes -X POST localhost:8080/equipes -H 'Content-type:application/json' -d '{"name":"BigData"}' > $fichier
diff $fichier new_equipe.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#put equipe
echo -e "\n ### test changer une equipe ### \n"
curl -X PUT localhost:8080/equipes/12 -H 'Content-type:application/json' -d '{"id":"12", "name":"IAMD"}' > $fichier
diff $fichier change_equipe.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#put match 
echo -e "\n ### test changer un match (changer le score) ### \n"
curl -X PUT localhost:8080/matches/4 -H 'Content-type:application/json' -d '{"id":4, "equipeDomicileName" : "IL", "equipeDomicileId" : 2, "equipeInviteName" : "ISS", "equipeInviteId" : 1, "status" : "EN_COURS", "date" : "02/09/18, 10:11", "competitionId" : 3, "equipeDomicileScore" : 10, "equipeInviteScore" : 200}'   > $fichier
diff $fichier change_match_4.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#get matches/{id}/STATUT
echo -e "\n ### test changer un statut de match via lien ### \n"
curl get localhost:8080/matches/4/fini  > $fichier
diff $fichier change_match_4_statut.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#get competitions/3 
echo -e "\n ### test vérifier que ça a impacté la compétition liée ### \n"
curl localhost:8080/competitions/3 > $fichier
diff $fichier new_compet_3.txt > diff.txt 
if (cat diff.txt | grep ">") 
then 
	echo -e " \n ==> il y a des différences : " 
	cat diff.txt
else 
	echo -e "\n ==> pas de différence : test VALIDÉ\n"
fi

#delete competitions/{id}
echo -e "\n ### test suppression d'une competitions ### \n"
curl -X DELETE localhost:8080/competitions/11
curl localhost:8080/competitions/11
echo -e "\n ==> La competition a bien été supprimée : test VALIDÉ\n"
