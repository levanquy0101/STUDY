import React, { useState, useEffect } from 'react';
import Moment from 'moment';

function List(props) {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/orders')
            .then(res => res.json())
            .then(data => setOrders(data))
    }, []); // useEffect chỉ chạy một lần khi component được render

    return (
        <div>
            <h2>Danh sách</h2>
            <table>
                <thead>
                    <tr>
                        <th>Item</th>
                        <th>Item</th>
                        <th>Item</th>
                        <th>Item</th>
                        <th>Item</th>
                        <th>Item</th>
                        <th>Item</th>
                        <th>Item</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        orders.map((item, index) => (
                            <tr key={item.id}>
                                <td>{++index}</td>
                                <td>{item.products.name}</td>
                                <td>{item.products.price}</td>
                                <td>{item.products.type}</td>
                                <td>{Moment(item.buyDate).format("DD/MM/yyyy")}</td>
                                <td>{item.number}</td>
                                <td>{item.total}</td>
                                <td>
                                    <button>Update</button>
                                    <button>Delete</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    );
}

export default List;