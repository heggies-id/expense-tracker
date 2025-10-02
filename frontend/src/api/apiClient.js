import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; 

const apiClient = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      config.headers['Authorization'] = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

apiClient.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;

    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const refreshToken = localStorage.getItem('refreshToken');
        
        const response = await axios.post(`${API_URL}/auth/refresh`, {
          token: refreshToken,
        });

        const { accessToken } = response.data;
        
        localStorage.setItem('accessToken', accessToken);
        
        apiClient.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
        
        return apiClient(originalRequest);

      } catch (refreshError) {
        console.error("Session expired. Please log in again.");
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        window.location.href = '/login'; 
        return Promise.reject(refreshError);
      }
    }
    
    return Promise.reject(error);
  }
);

export default apiClient;