import { useForm } from 'react-hook-form';
import { toast } from "react-toastify";
import * as OrderService from "../services/OrderService"
import * as ProductService from "../services/ProductService"
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import { isBefore } from 'date-fns';

function Create(props) {
    const navigate = useNavigate();
    const [productList, setProductList] = useState([]);

    const { register, handleSubmit, formState: { errors } } = useForm()
        ;
    const onSubmit = async (data) => {
        try {
            data.product = JSON.parse(data.product);
            await OrderService.save(data);
            console.log(data);
        } catch (e) {
            toast.error("Thêm mới thất bại")
            return;
        }
        toast.success("Thêm mới thành công")
        navigate("/")
    };

    useEffect(() => {
        getAll();
    }, []);

    const getAll = async () => {
        const temp = await ProductService.getAll();
        setProductList(temp);
    }

    const validateDate = value => new Date(value) < new Date().setHours(0, 0, 0, 0) || 'Ngày mua phải trước ngày hiện tại';


    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div className='add-item'>
                        <h2>Tạo mới</h2>
                        <form onSubmit={handleSubmit(onSubmit)} className='form'>
                            <input {...register('buyDate', { required: "Trường phải nhập!", validate: validateDate })} type='date' />
                            {errors.buyDate && <small>{errors.buyDate.message}</small>}

                            <input {...register('number', { required: "Trường phải nhập!", min:{ value: 1, message:"Số phải lớn hơn 0"}, pattern: {value: /^[1-9]\d*$/, message: "Phải là số nguyên!"}})}placeholder='Nhập số lượng' type='number' />
                            {errors.number && <small>{errors.number.message}</small>}

                            <input {...register('total', { required: "Trường phải nhập!" })} placeholder='Nhập tổng giá' />
                            {errors.total && <small>{errors.total.message}</small>}

                            <select {...register('product', { required: "Trường phải nhập!" })}>
                                <option value="">--Lựa chọn sản phẩm --</option>
                                {productList.map((item, index) => (
                                    <option key={item.id} value={JSON.stringify(item)}>{item.name}</option>
                                ))}
                            </select>
                            {errors.product && <small>{errors.product.message}</small>}

                            <button type='submit'>Gửi</button>
                        </form>
                    </div>
                    <button onClick={() => navigate("/")} className='btn back'>Quay lại</button>
                </div>
            </div>
        </div>
    );
}

export default Create;