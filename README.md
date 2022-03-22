# Praktische-Erfahrung
Hier sind Codes, die meine praktische Erfahrung zeigen.

In diesem Projekt wird folgendes gemacht. Es gibt zwei JSON-Dateien. In der einen Datei stehen die Ankünfte der Züge der S-Bahn-Station von Ulm-Hauptbahnhof. In der anderen Datei stehen die Abfahrten der jeweiligen Züge. Aus diesen beiden Dateien wird eine Liste erstellt, in der zu jeder Ankunft die Abfahrt in der anderen Liste gefunden wird. Danach wird berechnet, wie viele Gleise für diese Station benötigt werden. 

Ich habe versucht, den Code möglichst effizient zu machen. Ich habe die beiden JSON-Dateien eingelesen und in eine LinkedList eingetragen. Danach habe ich bei jedem Schleifendurchlauf das Element, dass nicht mehr gesucht oder für den gesucht werden musste, aus der Liste entfernt.
