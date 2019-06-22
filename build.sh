find src -name \*.java -print > fileToBuild.list
javac -Werror -g:none -d ./out/dev @fileToBuild.list
cp -r src/fr/space/Ressources/ out/dev/fr/space
cd ./out/dev
jar cfe projet.jar fr.space.Main.Run fr/space
echo "Le build en .jar a été généré à l'emplacement $(pwd)/projet.jar.
Utiliser : java -jar $(pwd)/projet.jar"
