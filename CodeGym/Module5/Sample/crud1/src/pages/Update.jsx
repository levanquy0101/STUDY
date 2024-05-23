import { useForm } from 'react-hook-form';
import { toast } from "react-toastify";
import * as OrderService from "../services/OrderService"
import * as ProductService from "../services/ProductService"
import { useNavigate } from 'react-router-dom';
import { useParams, useLocation } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';

function Update(props) {
    const location = useLocation();
    const [data, setData] = useState(location.state)
    const navigate = useNavigate();

    const [productList, setProductList] = useState([]);

    const { register, handleSubmit, formState: { errors } } = useForm()
        ;
    const onSubmit = async (data) => {
        try {
            data.product = JSON.parse(data.product);
            await OrderService.update(data.id, data);
            console.log(data);
        } catch (e) {
            toast.error("Cập nhật thất bại!")
            return;
        }
        toast.success("Cập nhật thành công")
        navigate("/")
    };

    useEffect(() => {
        console.log(data);
        getAll();
    }, []);

    const getAll = async () => {
        const temp = await ProductService.getAll();
        setProductList(temp);
    }


    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div className='add-item'>
                        <h2>Sửa thông tin</h2>
                        <form onSubmit={handleSubmit(onSubmit)} className='form'>
                            <input {...register('id', { required: true })} type='hidden' defaultValue={data.row.id} />
                            <input {...register('buyDate', { required: true })} type='date' defaultValue={data.row.buyDate} />
                            <input {...register('number', { required: true })} placeholder='Nhập số lượng' defaultValue={data.row.number} />
                            <input {...register('total', { required: true })} placeholder='Nhập tổng giá' defaultValue={data.row.total} />
                            <select {...register('product', { required: true })}>
                                <option value="">--Lựa chọn sản phẩm --</option>
                                {productList.map((item, index) => (
                                    <option key={item.id} value={JSON.stringify(item)} selected={item.id === data.row.product.id}>{item.name}</option>
                                ))}
                            </select>
                            <button type='submit'>Gửi</button>
                        </form>
                    </div>
                    <button onClick={() => navigate("/")} className='btn back'>Quay lại</button>
                </div>
            </div>
        </div>
    );
}

export default Update;