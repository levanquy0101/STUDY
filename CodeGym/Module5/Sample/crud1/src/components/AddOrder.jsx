import React, { useState } from 'react';
import Moment from 'moment';

function AddOrder() {
    const [newOrder, setNewOrder] = useState({
        productName: '',
        price: '',
        type: '',
        buyDate: '',
        number: '',
        total: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setNewOrder(prevOrder => ({
            ...prevOrder,
            [name]: value
        }));
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newOrder)
        })
        .then(res => res.json())
        .then(data => {
            // Do something with the response if needed
            console.log('New order added:', data);
            setNewOrder({
                productName: '',
                price: '',
                type: '',
                buyDate: '',
                number: '',
                total: ''
            });
        })
        .catch(error => console.error('Error:', error));
    }

    return (
        <div>
            <h2>Add New Order</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="productName" value={newOrder.productName} onChange={handleChange} placeholder="Product Name" required />
                <input type="number" name="price" value={newOrder.price} onChange={handleChange} placeholder="Price" required />
                <input type="text" name="type" value={newOrder.type} onChange={handleChange} placeholder="Type" required />
                <input type="date" name="buyDate" value={newOrder.buyDate} onChange={handleChange} required />
                <input type="number" name="number" value={newOrder.number} onChange={handleChange} placeholder="Number" required />
                <input type="number" name="total" value={newOrder.total} onChange={handleChange} placeholder="Total" required />
                <button type="submit">Add Order</button>
            </form>
        </div>
    );
}

export default AddOrder;
