fichier="temp_test.txt"

#get competitions 
echo "test get competitions"
curl localhost:8080/competitions > $fichier
diff $fichier competitions.txt

fichier="equipes.txt"

#get competitions
echo "test get equipes"
curl localhost:8080/equipes > $fichier
diff $fichier equipes.txt

fichier="matches.txt"

#get competitions
echo "test get matches"
curl localhost:8080/matches > $fichier
diff $fichier matches.txt
