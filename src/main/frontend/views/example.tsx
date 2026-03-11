import {
    TextField,
    Button,
    VerticalLayout,
    Grid,
    GridColumn,
    Notification,
    HorizontalLayout
} from "@vaadin/react-components";
import {ViewConfig} from "@vaadin/hilla-file-router/types.js";
import {useEffect, useState} from "react";
import {ProductService} from "Frontend/generated/endpoints";

export const config: ViewConfig = {
    title: 'Ficha de Asegurados',
    menu : { title: 'Prueba Hilla', exclude: false},
};
type Product = {
    id: number | 0;
    name: string;
    price: number;
    stock: number;
};
export default function ProductsView(){
    const [products, setProducts] = useState<Product[]>([]);
    const [productSelected, setProductSelected] = useState<Product|null>(null);

    useEffect(()=>{
        ProductService.findAll().then(data=>{
            setProducts(data?.filter((p): p is Product => p !== undefined)??[])
        });
    },[]);
    const handleEdit = (p: Product)=>{
        setProductSelected(p);
    };
    const handleSave = (product: Product) => {

        ProductService.save(product).then(saved => {

            if (product.id){
                setProducts(prev =>
                    prev.map(p => p.id === saved?.id ? saved : p )
                        .filter((p): p is Product => p !== undefined));
            }else {
                setProducts(prev =>
                    [...prev, saved].filter((p): p is Product => p !== undefined));
            }
            setProductSelected(null);
        });
    };
    const handleDelete = (id: number) =>{
        ProductService.delete(id).then(()=>{
            setProducts(products.filter(p=> p.id !==id))
        });
    };
    const handleNew= ()=>{
        setProductSelected({ id: 0, name: "", price: 0, stock: 0})
    };
    return(
        <div>
            <Button onClick={handleNew}>Add new Product</Button>
            <ProductsTable
                products={products}
                onEdit={handleEdit}
                onDelete={handleDelete}
            />
            <ProductForm product={productSelected} onSave={handleSave}/>
        </div>
    );
}

function ProductForm({product, onSave}:{
    product: Product | null;
    onSave: (p: Product) => void;
}) {

    const [name, setName] = useState(product?.name ?? "");
    const [price, setPrice] = useState(product?.price ?? 0);
    const [stock, setStock] = useState(product?.stock ?? 0);

    useEffect(() => {
        setName(product?.name ?? "");
        setPrice(product?.price ?? 0);
        setStock(product?.stock ?? 0);
    }, [product]);
    return (
        <VerticalLayout style={{'margin': '2rem'}}>
            <TextField
                label="name"
                value={name}
                onValueChanged={e => setName(e.detail.value)}
            />

            <TextField
                label="price"
                value={String(price)}
                onValueChanged={e => setPrice(Number(e.detail.value))}
            />

            <TextField
                label="stock"
                value={String(stock)}
                onValueChanged={e => setStock(Number(e.detail.value))}
            />

            <HorizontalLayout className="btn-group">
                <Button onClick={()=> Notification.show("Canceling action...")}>Cancel</Button>
                <Button onClick={()=> onSave({id: product?.id ?? 0,name, price, stock})}>Save</Button>
            </HorizontalLayout>
        </VerticalLayout>
    );
}


function ProductsTable({products, onEdit, onDelete}:{
    products: Product[];
    onEdit: (p: Product) => void;
    onDelete: (id: number) => void;
})
{
    return(
        <Grid items={products}>
            <GridColumn path="id" header="ID"/>
            <GridColumn path="name" header="Name"/>
            <GridColumn path="price" header="Price"/>
            <GridColumn path="stock" header="Stock"/>
            <GridColumn path="action" header="Actions">
                {({item}) =>(
                    <div>
                        <button style={{margin: '1rem'}} onClick={()=>onEdit(item)}>Editar</button>
                        <button style={{margin: '1rem'}} onClick={()=>onDelete(item.id)}>Eliminar</button>
                    </div>
                )}
            </GridColumn>
        </Grid>
    );
}