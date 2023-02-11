import { IonApp, IonContent, IonFab, IonFabButton, IonHeader, IonIcon, IonPage, IonTitle, IonToolbar } from "@ionic/react";
import { camera } from "ionicons/icons";
import Login from "./Login";

function App(){

    return(
        <IonPage>
            <Login/>
        </IonPage>
    )
}

export default App;