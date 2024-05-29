import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import Moment from 'moment';
import { useQuery } from 'react-query';
import axios from 'axios';
import CustomTable from '../hooks/useTable';
import { toast } from 'react-toastify';

function List() {
    const [itemDel, setItemDel] = useState({});
    const [filterParams, setFilterParams] = useState({ type: '', startDate: '', endDate: '' });

    const { data: orders, isLoading, refetch } = useQuery(['orders', filterParams], async () => {
        const { type, startDate, endDate } = filterParams;
        const response = await axios.get('http://localhost:8000/orders', 
       );
        return response.data;
    });
    

    const handleChangeFilter = (field, value) => {
        setFilterParams({ ...filterParams, [field]: value });
    };

    const handleChangeDel = (item) => {
        setItemDel(item);
    };

    const removeItem = async () => {
        await axios.delete(`/api/orders/${itemDel.id}`);
        setItemDel({});
        toast.success("Xóa thành công");
        refetch();
    };

    const columns = [
        { header: 'ID', key: 'id', render: (row) => row.id },
        { header: 'Tên sản phẩm', key: 'product', render: (row) => row.product.name },
        { header: 'Giá', key: 'price', render: (row) => row.product.price },
        { header: 'Loại', key: 'type', render: (row) => row.product.type },
        { header: 'Ngày mua', key: 'date', render: (row) => Moment(row.buyDate).format('DD/MM/YYYY') },
        { header: 'Số lượng', key: 'number', render: (row) => row.number },
        { header: 'Tổng tiền', key: 'total', render: (row) => row.total },
        { header: 'Hành động', key: 'action', render: (row) => 
            (<>
                <Link to={`/update/${row.id}`} state={{ row: row }} className='btn update'>Sửa</Link>            
                <button onClick={() => handleChangeDel(row)} className='btn delete'>Xóa</button>
            </>) },
        
    ];

    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div>
                        <Link to="/create" className='btn add'>Thêm mới</Link>
                        <h2>Danh sách</h2>
                        <div className='fillter'>
                            <div className='item'>
                                <label htmlFor="">
                                    Loại sản phẩm:
                                    <select name="" onChange={(e) => handleChangeFilter('type', e.target.value)} aria-label="Default select example">
                                        <option value="">--Chọn loại sản phẩm--</option>
                                        <option value="Điện thoại">Điện thoại</option>
                                        <option value="Laptop">Laptop</option>
                                    </select>
                                </label>
                            </div>
                            <div className='item'>
                                <label htmlFor="">
                                    Ngày bắt đầu:
                                    <input type="date" onChange={(e) => handleChangeFilter('startDate', e.target.value)} />
                                </label>
                                <label htmlFor="">
                                    Ngày kết thúc:
                                    <input type="date" onChange={(e) => handleChangeFilter('endDate', e.target.value)} />
                                </label>
                            </div>
                        </div>
                        <CustomTable data={orders || []} itemsPerPage={4} columns={columns} />
                        {isLoading && 
                            <div className='spinner-border' role='status' style={{textAlign: "center"}}>
                                <span className='sr-only'>Loading ...</span>
                            </div>}
                        {itemDel.id &&
                            <div className="modal-content">
                                <span className="close" onClick={() => setItemDel({})}>&times;</span>
                                <p>Are you sure you want to delete this item?</p>
                                <div className="button-container">
                                    <button className="btn delete" onClick={removeItem}>Xóa</button>
                                    <button className="btn cancel" onClick={() => setItemDel({})}>Trở về</button>
                                </div>
                            </div>
                        }    
                    </div>        
                </div>
            </div>
        </div>
    );
}

export default List;
