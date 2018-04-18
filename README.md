# Einkaufsliste
Einkaufsliste Prototyp als Andriod App entwickelt mit Java 

## Verwendete Technologie und Vergleich
### Vergleich von möglichen Technologien
#### Diffsync + Couchbase 
Eine mögliche Lösung ist die Verwendung von **Couchbase** als Datenbank zur Persistierung mit **Diffsync** als Framework zur Synchronisierung der Datensätze. Mithilfe von Couchbase können leicht Daten persistiert und repliziert werden. Für eine Replikation müssen selber Server verwendet und eigene Couchbase Instanzen erstellt werden.  
Diffsync ist ein Framework für Node.js und kann Daten zwischen Clients synchron halten. Diffsync verwendet im Hintergrund zur Synchronisation die **Differential Synchronization** Methode.  
Der Nachteil des gesamten Ansatzes ist, dass alle Teile erst geschrieben und konfiguriert werden müssen.  

#### Firebase Realtime Database

Eine weitere Lösung ist von **Firebase** die **Realtime Database** zu verwenden. Firebase ist ein Service von Google mit den modernen Apps geschrieben werden. Der Service bietet Speicheroptionen, Authentifizierung, Werbung, Analysetools und noch weitere Dienste an. Zusätzlich bietet Firebase Schnittstellen neben Andriod und iOS für Web (JS), Java, Unity und noch Sprachen.  Mit der Realtime Database können Daten online gehostet werden und dank einer eingebauten Synchronisationmethode werden die Daten auf allen Geräten synchron gehalten. Realtime Database bietet Unterstützung für Android Apps und Java. Der Vorteil dieses Ansatzes ist, dass Synchronisierung und Datenpersistierung ausgelagert werden und daher nicht selber geschrieben bzw. konfiguriert werden muss.

#### Firebase Cloud Firestore

Die letzte Lösung kommt ebenfalls von Firebase und heißt **Cloud Firestore**. Cloud Firestore ist sehr ähnlich der Realtime Database und unterscheidet sich nur in wenigen Punkten. Zum einen wird bei Cloud Firestore eine Dokumentorientierte **NoSQL** Datenbank verwendet und damit sind komplexer Daten einfacher abzuspeichern. Zum zweiten befindet sich Cloud Firestore derzeit in der Beta Phase und es können Bugs bzw. Fehler vermehrt auftreten. Zum Dritten gäbe es bei Cloud Firestore eine andere Preispolitik. Kosten verwenden bei Realtime Database aufgrund der Bandbreite und der Speicherauslastung berechnet während bei Cloud Firestore die Operationen(Read/Write) verrechnet werden. Aufgrund dieser Nachteile habe ich gegen Cloud Firestore entschieden.

### Verwendete Technologie (Firebase Realtime Database)
Für die Implementierung der App habe ich mich für die Lösung [Firebase Realtime Database](#firebase-realtime-database) entschieden. Von Realtime Database wird eine Datenbank verwaltet und mittels der bereitgestellten Library wird die Datenbank bearbeitet und synchron gehalten. Zusätzlich wird eine Android App mit Java als Sprache im Hintergrund. Es gibt auch bei Realtime Database auch Nachteile bzw. Probleme die während der Entwicklung aufgetretten sind. Es gibt auch bei Realtime Database auch Nachteile bzw. Probleme die während der Entwicklung aufgetreten sind. In Hinsicht auf Replikation und Sicherung gibt es die Optionen nur mit einem kostenpflichtigen Abo.  Außerdem hatte ich das Problem, dass nach längerer Inaktivität der Datenbank diese anscheinend gelöscht wird und erst eine Datenbank  neu erstellen und konfigurieren werden muss.

## Sychnronisierung der Daten und Design des Systems
Die Synchronisierung der Daten wird mittels Realtime Database von Firebase abgewickelt. 
Realtime Database bietet eine Online-Datenbank an mit limitieren Zugriffen und Speicherplatz dafür aber kostenlos und kann Änderungen in Realtime verarbeiten. Daher kann Realtime Database die Synchronisation zwischen Geräte eigenständig übernehmen. Welche Methode zur Synchronisation der Datenbanken verwendet wird, ist nicht beschrieben auf einer offiziellen Webseite.  
Im Hinsicht auf die Replikation der Daten im Besonderen auf die Sicherung dieser gibt es von Realtime Database einen Möglichkeit Backups der Datenbank anzulegen. Diese Option ist aber mit Kosten verbunden und habe ich daher nicht ausprobiert oder umgesetzt. Es kann eine weitere Instanz der Datenbank manuell gestartet werden, allerdings ist dies auch wieder mit Kosten verbunden.  
Das gewählte Design ist eine Client-Server Architektur mit Firebase/Realtime Database als Serverkomponente. Die Clients sind die Benutzer mit der geschriebenen Android App.  
## Firebase Dokumentation (Schnittstelle)
Die Datenbank für die App ist wie folgt strukturiert:  
![Imgur](https://i.imgur.com/N38mRae.png)  
Es gibt eine Unterordnung „lists“, in denen alle Liste gespeichert werden. Dann wird in einer Liste(list1) jeder Eintrag mit einem eindeutigen Key, bereitgestellt durch die Firebase Library, mit dem Text als Value gespeichert. Derzeit kann nur eine Liste bearbeitet werden.

Im Programm ist für die Anbindung an die Datenbank eine Variable vom Typ DatabaseReference. ```
this.database = FirebaseDatabase.getInstance().getReference("lists"); ```
Dann wird ein Listener für die Datenbank geschrieben um Daten auszulesen, einzufügen, bearbeiten und zu löschen. ``` this.database.child("list1").addChildEventListener ```. In diesem Listener gibt es dann eigene Methoden bei einem Added-, Changed-, Removed-Event. Beim Starten der App verwenden die Daten abgerufen, in dem die Daten über das Added-Event abgerufen werden.

Quellen:  
http://forum.tvac.in/ bzw. Videos (https://www.youtube.com/watch?v=6Iy7crsnVhA&list=PLGCjwl1RrtcSi2oV5caEVScjkM6r3HO9t)
https://firebase.google.com/docs/database/android/start/?authuser=0  


