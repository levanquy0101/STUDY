import { useForm } from 'react-hook-form';
import {toast} from "react-toastify";

function Create(props) {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const onSubmit = (data) => {
        console.log(data);
      };
    return (
        <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input {...register('a',{required: true})} />
                <input {...register('b',{required: true})} />
                <input {...register('c',{required: true})} />
                <input {...register('d',{required: true})} />
                <button type='submit'>Gửi</button>
            </form>
        </div>
    );
}

export default Create;