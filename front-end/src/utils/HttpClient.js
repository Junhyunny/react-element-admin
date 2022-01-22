import axios from "axios";

export const post = async (path, requestBody, params) => {
    try {
        const {data} = await axios.post(`/backend_service${path}`, requestBody, {
            params
        });
        return data;
    } catch (error) {
        throw new Error(error);
    }
};

export default {post};