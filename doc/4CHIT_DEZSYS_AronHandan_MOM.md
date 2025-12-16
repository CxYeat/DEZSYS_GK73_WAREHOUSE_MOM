# MidEng 7.3 Message Oriented Middleware (Grundlagen plus Vertiefung) – 4h

___

**Autor:** Aron Handan

**Datum:** 1.12.2025

___

# 1.6 Fragestellung für Protokoll – Message Oriented Middleware (MOM)

## 1. Nennen Sie mindestens 4 Eigenschaften der Message Oriented Middleware?

- **Asynchrone Kommunikation:** Sender und Empfänger müssen nicht gleichzeitig aktiv sein.  
- **Entkopplung von Sender und Empfänger:** Systeme müssen nicht direkt miteinander verbunden sein.  
- **Zuverlässigkeit / Persistenz:** Nachrichten können gespeichert werden, falls Empfänger offline ist.  
- **Skalierbarkeit:** Unterstützung mehrerer Sender und Empfänger effizient möglich.  
- **Transparenz:** Netzwerkdetails, Protokolle und Standort der Teilnehmer werden versteckt.  
- **Unterstützung verschiedener Nachrichtenformate:** z. B. Text, Bytes, Objekte.  

## 2. Was versteht man unter einer transienten und synchronen Kommunikation?

- **Transiente Kommunikation:**  
  - Nachricht wird nur gesendet, wenn Empfänger aktiv ist.  
  - Keine Speicherung im System – Nachricht geht verloren, wenn Empfänger offline ist.  
- **Synchrone Kommunikation:**  
  - Sender wartet auf Antwort des Empfängers, bevor er fortfährt.  
  - Typisch für Request-Reply-Szenarien.  

## 3. Beschreiben Sie die Funktionsweise einer JMS Queue

- **Modell:** Point-to-Point (PTP)  
- **Ablauf:**  
  1. Producer sendet Nachricht an Queue.  
  2. Queue speichert Nachricht, bis ein Consumer sie abruft.  
  3. Consumer verarbeitet die Nachricht und bestätigt Empfang.  
- **Eigenschaften:**  
  - Jede Nachricht wird **genau einmal** verarbeitet.  
  - Sender und Empfänger sind **entkoppelt**.  
  - Nachrichten können **temporär oder persistent** gespeichert werden.  

## 4. JMS Overview – Beschreiben Sie die wichtigsten JMS Klassen und deren Zusammenhang

- **ConnectionFactory:** Erzeugt Connections zum JMS-Provider.  
- **Connection:** Verbindung zum Messaging-System.  
- **Session:** Kontext zum Erstellen und Empfangen von Nachrichten.  
- **Destination:** Ziel der Nachricht (Queue oder Topic).  
- **MessageProducer:** Erstellt und sendet Nachrichten.  
- **MessageConsumer:** Empfängt Nachrichten.  
- **Message:** Nachricht selbst, z. B. `TextMessage`, `ObjectMessage`.  

**Zusammenhang:**  
`ConnectionFactory → Connection → Session → Destination → Producer/Consumer → Message`  

## 5. Beschreiben Sie die Funktionsweise eines JMS Topic

- **Modell:** Publish/Subscribe (Pub/Sub)  
- **Ablauf:**  
  1. Producer veröffentlicht Nachricht auf Topic.  
  2. Alle angemeldeten Subscriber erhalten Nachricht.  
- **Eigenschaften:**  
  - Nachrichten werden **gleichzeitig an mehrere Empfänger** gesendet.  
  - Unterstützt **asynchrone Benachrichtigungen**.  
  - Subscriber können **durable** (auch offline empfangen) oder **non-durable** sein.  

## 6. Was versteht man unter einem lose gekoppelten verteilten System? Nennen Sie ein Beispiel dazu. Warum spricht man hier von lose?

- **Definition:** Systeme sind **unabhängig voneinander**, tauschen Daten über standardisierte Schnittstellen aus.  
- **Beispiel:** JMS-basierte Kommunikation zwischen Lagerverwaltung und Webshop.  
- **Warum „lose“?**  
  - Sender muss nichts über Empfänger wissen (z. B. Standort, Implementierung).  
  - Änderungen an einem System wirken **nicht direkt auf andere**.

---

## Apache Kafka als Container

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-17-55-08-image.png)

Zuerst muss man das Image pullen um ein Container anschließend zu erstellen.

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-18-08-26-image.png)

Anschließend kann man den Container konfigurieren, hier laut Angabe wird 9092:9092 empfohlen für die Ports, den Rest kann man auslassen für das Erste.

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-18-09-10-image.png)

Hier die Logs für das erstellen des Container um sicher zu gehen, dass der Container ohne Probleme erstellt wird

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-18-12-50-image.png)

Im Termial mit dem Command `docker exec -it <container_name> bash` hat man Zugriff auf das "innere Leben" von Container und kann scripts ausführen

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-18-14-40-image.png)

Hier wird ein Topic erstellt namens `quickstart-events`. Anschließend, werden Logs manuell geschrieben und ausgeführt.

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-18-42-31-image.png)

Mit den Basis-Klassen, die per Repo zur Verfügung gestellt werden, kann man per URL also einem definierten REST-Endpoint ein Log in das Topic reinschreiben

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-18-42-47-image.png)

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-13-18-43-27-image.png)

---

## GK

Die Kommunikation zwischen einem Lager und dem Zentralrechner erfolgt über eine **JMS Queue**. Ein Lager, zum Beispiel das Hauptlager in Hamburg, erstellt Nachrichten, die Informationen über die vorhandenen Produkte enthalten. Diese Nachrichten werden in die Queue geschickt.

Der Zentralrechner liest die Nachrichten aus der Queue, sobald sie eintreffen. Da es sich um eine **Queue handelt**, wird jede Nachricht **genau einmal** verarbeitet, und der Sender muss nicht gleichzeitig online sein. So sind Lager und Zentralrechner **entkoppelt**, also unabhängig voneinander.

---

### **Ausgabe der empfangenen Daten am Zentralrechner**

Am Zentralrechner werden die empfangenen Nachrichten direkt angezeigt, zum Beispiel in der **Konsole** oder in einer **Log-Datei**. Dort kann man sofort sehen, welches Lager welche Produkte in welcher Menge gemeldet hat, inklusive Zeitstempel. So behält man einen **übersichtlichen Überblick über alle Lagerbestände**.

---

## EK

### **Zusammensetzung der Daten aller Lagerstandorte in einer zentralen JSON/XML-Struktur**

Um die Bestände aller Lager zentral zu erfassen, sammelt der Zentralrechner die von den einzelnen Lagern gesendeten Daten. Diese Daten werden in einer **einheitlichen Struktur** zusammengeführt, zum Beispiel in **JSON** oder **XML**.

Jeder Datensatz enthält Informationen wie Lager-ID, Name, Adresse, Stadt, Land, Produkte mit ID, Name, Kategorie, Menge und Einheit sowie den Zeitstempel. Der Zentralrechner erstellt daraus eine **gesammelte Übersicht**, die alle Lager vereint. Dadurch kann man jederzeit auf einen Blick erkennen, welche Produkte in welchem Lager verfügbar sind.

---

### **Implementierung der REST-Schnittstelle am Zentralrechner**

Am Zentralrechner wird eine **REST-Schnittstelle** bereitgestellt, über die andere Systeme oder Benutzer auf die gesammelten Lagerdaten zugreifen können.

- Die Schnittstelle bietet **Endpunkte**, z. B. für alle Lager oder für ein bestimmtes Lager.

- Sie stellt die Daten in **JSON oder XML** bereit, sodass sie leicht von externen Anwendungen gelesen und weiterverarbeitet werden können.

- Wenn ein Lager neue Daten sendet, kann der Zentralrechner diese automatisch aktualisieren, sodass die REST-Schnittstelle immer **aktuelle Informationen liefert**

Diese Schnittstelle ermöglicht es, dass zentrale Auswertungen, Berichte oder Dashboards jederzeit mit den neuesten Lagerbeständen versorgt werden, ohne dass man direkt auf einzelne Lager zugreifen muss.

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-16-14-40-43-image.png)

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-16-14-41-04-image.png)

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-16-14-41-16-image.png)

---

## Vertiefung

### **Implementierung der Kommunikation mit mehreren Lagerstandorten und dem Zentralrechner**

Die Kommunikation erfolgt so, dass **alle Lager gleichzeitig** ihre Bestandsdaten an den Zentralrechner senden. Jede Nachricht wird über eine **JMS Queue** in das System gelegt, sodass der Zentralrechner sie nacheinander abholt und verarbeitet.
Durch diese zentrale Sammlung kann der Rechner **alle Lagerstände gleichzeitig überwachen** und die Daten in einer **einheitlichen Struktur** zusammenfassen. Die Entkopplung über JMS sorgt dafür, dass Lager und Zentralrechner **unabhängig voneinander arbeiten** können.

---

### **Logging der Daten bei allen Lagerstandorten und dem Zentralrechner**

Jeder Lagerstandort protokolliert die gesendeten Nachrichten lokal, sodass nachvollziehbar ist, **wann welche Daten verschickt wurden**. 
Der Zentralrechner loggt ebenfalls alle empfangenen Nachrichten, inklusive Zeitstempel, Lager-ID und Produktinformationen. 
Dieses Logging stellt sicher, dass **alle Übertragungen überprüfbar und nachvollziehbar** sind, falls es zu Problemen oder Fehlern kommt.

---

### **Rückmeldung des Ergebnisses der Übertragung vom Zentralrechner an die Lagerstandorte (JMS Topic)**

Nachdem der Zentralrechner eine Nachricht eines Lagers verarbeitet hat, sendet er eine **Bestätigungsmeldung über ein JMS Topic** an alle Lager.

- Jede Rückmeldung enthält Informationen darüber, ob die Übertragung erfolgreich war.

- Die Nutzung eines Topics erlaubt, dass **mehrere Lager gleichzeitig** diese Rückmeldung erhalten können.

- So wissen die Lager jederzeit, dass ihre Daten **korrekt angekommen und verarbeitet** wurden.

### Producer

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-16-14-41-34-image.png)

### Consumer

![](C:\Users\aronh\AppData\Roaming\marktext\images\2025-12-16-14-42-18-image.png)

---

# Quellenverzeichnis

- Grundlagen Message Oriented Middleware: [Presentation](https://elearning.tgm.ac.at/pluginfile.php/119077/mod_resource/content/1/dezsys_mom_einfuehrung.pdf)

- Middleware: [Apache Kafka](https://kafka.apache.org/quickstart)

- [Apache Kafka | Getting Started](https://kafka.apache.org/documentation/#gettingStarted)
  
  https://medium.com/@abhishekranjandev/a-comprehensive-guide-to-integrating-kafka-in-a-spring-boot-application-a4b912aee62e https://spring.io/guides/gs/messaging-jms/ 
  https://medium.com/@mailshine/activemq-getting-started-with-springboot-a0c3c960356e 
  [Introduction of JMS | JMS | JMS message| JMS api | JMS client | JMS server | JMS Tutorial | JMS Example | Learn JMS](http://www.academictutorials.com/jms/jms-introduction.asp) 
  [The Java Message Service API](http://docs.oracle.com/javaee/1.4/tutorial/doc/JMS.html#wp84181) 
  [Java Message Service (JMS)](https://www.oracle.com/java/technologies/java-message-service.html) 
  [Getting Started with Java Message Service (JMS)](http://www.oracle.com/technetwork/articles/java/introjms-1577110.html) 
  https://spring.io/guides/gs/messaging-jms 
  https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-messaging.html 
  [Using JMS in Spring Boot](https://dzone.com/articles/using-jms-in-spring-boot-1)
