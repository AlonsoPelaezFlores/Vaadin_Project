import {FormItem, FormLayout, VerticalLayout} from "@vaadin/react-components";
import {ViewConfig} from "@vaadin/hilla-file-router/types.js";

export const config: ViewConfig ={
    menu: {title: 'Ficha Asalariado Hilla'}
}
export default function EmployeeView(){

    return(
        <div style={{padding: '1rem'}}>
            <h3 style={{margin:'1rem 0 1rem 0'}}>Fitta Assegurat</h3>
            <InsuredData/>
        </div>
    );
}


function InsuredData(){
    return(
        <VerticalLayout className="card-container" >
            <h4>Dades assegurat</h4>
            <FormLayout responsiveSteps={[{minWidth:'0',columns:1}]}>
                <FormItem>
                    <label slot="label">Nùm. assegurat</label>
                    <span >002434P</span>
                </FormItem>
                <FormItem>
                    <label slot="label">Nom assegurat</label>
                    <span>MORERA MARTINEZ,XAVIER</span>
                </FormItem>
            </FormLayout>
        </VerticalLayout>
    );
}