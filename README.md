
# Descrizione:
Questo repository è dedicato al mio esercizio personale con applicazioni basate su microservizi. Utilizzo questo spazio per sperimentare e migliorare le mie competenze nella progettazione, sviluppo e gestione di servizi distribuiti.

## Contenuto del Repository:
* [order-service](https://github.com/Light-251/microservices-tutorial/tree/master/order-service): gestionale riguardo ordnini, Utilizza mySql come Database.
* [inventory-service](https://github.com/Light-251/microservices-tutorial/tree/master/inventory-service): servizio per la gestione di un inventario, utilizza mySql come Database.
* [product-service](https://github.com/Light-251/microservices-tutorial/tree/master/product-service): servizio per la gestione dei prodotti, utilizza MongoDb come Database.

## [Discovery Server](https://github.com/Light-251/microservices-tutorial/tree/master/discovery-server):

Il `discovery-server` è un componente essenziale all'interno dell'architettura a microservizi. Si tratta di un server di discovery che svolge il ruolo di coordinatore per registrare e localizzare dinamicamente i microservizi all'interno del sistema distribuito.

### Funzionalità Principali:

- **Registrazione e Localizzazione:**
  Il discovery server consente ai microservizi di registrarsi durante l'avvio e di essere localizzati dagli altri servizi all'interno dell'ecosistema.

- **Gestione Dinamica:**
  Supporta la gestione dinamica dei servizi, consentendo l'aggiunta o la rimozione di microservizi senza impattare negativamente l'integrità del sistema.

- **Bilanciamento del Carico:**
  Fornisce servizi di bilanciamento del carico, migliorando l'affidabilità e la scalabilità del sistema.

### Utilizzo:

Il `discovery-server` semplifica notevolmente la creazione di un'architettura a microservizi, fornendo un meccanismo flessibile per la gestione della registrazione e della localizzazione dei servizi.

### Tecnologie Utilizzate:

- **Spring Boot:** Framework per la creazione di applicazioni Java basato su standard industriali.
- **MySQL:** Sistema di gestione di database relazionali open source.
- **MongoDB:** Database NoSQL orientato ai documenti.
