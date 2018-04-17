# Einkaufsliste
Einkaufsliste Prototyp als Andriod App entwickelt mit Java 

## Verwendete Technologie und Vergleich
### Vergleich von möglichen Technologien
#### Diffsync + Couchbase 
Eine mögliche Lösung ist die Verwendung von Couchbase als Datenbank zur Persistierung mit Diffsync als Framework zur Synchronisierung der Datensätze. Mithilfe von Couchbase können leicht Daten persistiert und repliziert werden. Für eine Replikation müssen selber Server verwendet und eigene Couchbase Instanzen erstellt werden.  
Diffsync ist ein Framework für Node.js und kann Daten zwischen Clients synchron halten. Diffsync verwendet im Hintergrund zur Synchronisation die **Differential Synchronization** Methode.  
Der Nachteil des gesamten Ansatzes ist, dass alle Teile erst geschrieben und konfiguriert werden müssen.  

#### Firebase RealtimeDatabase

Eine weitere Lösung ist von Firebase die Realtime Database zu verwenden. Firebase ist ein Service von Google mit den modernen Apps geschrieben werden. Der Service bietet Speicheroptionen, Authentifizierung, Werbung, Analysetools und noch weitere Dienste an. Zusätzlich bietet Firebase Schnittstellen neben Andriod und iOS für Web (JS), Java, Unity und noch Sprachen.  Mit der Realtime Database können Daten online gehostet werden und dank einer eingebauten Synchronisationmethode werden die Daten auf allen Geräten synchron gehalten. Realtime Database bietet Unterstützung für Android Apps und Java. Der Vorteil dieses Ansatzes ist, dass Synchronisierung und Datenpersistierung ausgelagert werden und daher nicht selber geschrieben bzw. konfiguriert werden muss.

#### Firebase Cloud Firestore

Die letzte Lösung kommt ebenfalls von Firebase und heißt Cloud Firestore. Cloud Firestore ist sehr ähnlich der Realtime Database und unterscheidet sich nur in wenigen Punkten. Zum einen wird bei Cloud Firestore eine Dokumentorientierte NoSQL Datenbank verwendet und damit sind komplexer Daten einfacher abzuspeichern. Zum zweiten befindet sich Cloud Firestore derzeit in der Beta Phase und es können Bugs bzw. Fehler vermehrt auftreten. Zum Dritten gäbe es bei Cloud Firestore eine andere Preispolitik. Kosten verwenden bei Realtime Database aufgrund der Bandbreite und der Speicherauslastung berechnet während bei Cloud Firestore die Operationen(Read/Write) verrechnet werden. Aufgrund dieser Nachteile habe ich gegen Cloud Firestore entschieden.

### Verwendete Technologie (Firebase Realtime Database)
in der Weboberfläche einen Punkt namens „Sicherungen“. Dieser Punkt kann aber in der kostenlosen Version von Realtime Database nicht verwendet werden. Dafür ist ein kostenpflichtiges oder ein _Pay-As-You-Go_ Konto benötigt.

## Sychnronisierung der Daten und Design des Systems
Die Synchronisierung der Daten wird mittels **Realtime Database** von **Firebase** abgewickelt. 
Realtime Database bietet eine Online-Datenbank an mit limitieren Zugriffen und Speicherplatz dafür aber kostenlos und kann Änderungen in Realtime verarbeiten. Daher kann Realtime Database die Synchronisation zwischen Geräte eigenständig übernehmen. Welche Methode zur Synchronisation der Datenbanken verwendet wird, ist nicht beschrieben auf einer offiziellen Webseite.  
Im Hinsicht auf die Replikation der Daten im Besonderen auf die Sicherung dieser gibt es von Realtime Database einen Möglichkeit Backups der Datenbank anzulegen. Diese Option ist aber mit Kosten verbunden und habe ich daher nicht ausprobiert oder umgesetzt. Es kann eine weitere Instanz der Datenbank manuell gestartet werden, allerdings ist dies auch wieder mit Kosten verbunden.  
Das gewählte Design ist eine Client-Server Architektur mit Firebase/Realtime Database als Serverkomponente. Die Clients sind die Benutzer mit der geschriebenen Androidapp.  
## Firebase Dokumentation (Schnittstelle)

DB Struktur
Library für Java


// Zusätzlich habe ich das Problem, dass nach längerer Inaktivität der Datenbank die Datenbank anscheinend gelöscht wird und ich eine Datenbank erst neu erstellen und konfigurieren muss.


Quellen:
