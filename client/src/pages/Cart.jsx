import React from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import { X } from 'lucide-react';

const Cart = () => {
  // Kelajakda dynamic cart data uchun tayyor (hozircha statik)
  const cartItems = [
    {
      id: 1,
      image: './p1_product.png',
      title: 'Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse',
      price: 45,
      quantity: 1,
    },
  ];

  return (
    <div className="bg-gray-900 text-white min-h-screen flex flex-col">
      <Navbar />

      <div className="flex-grow p-8">
        <table className="w-full max-w-5xl mx-auto text-center">
          <thead className="border-b border-gray-600">
            <tr>
              <th className="px-5 py-3">Product</th>
              <th className="px-5 py-3">Title</th>
              <th className="px-5 py-3">Price</th>
              <th className="px-5 py-3">Quantity</th>
              <th className="px-5 py-3">Total</th>
              <th className="px-5 py-3">Remove</th>
            </tr>
          </thead>
          <tbody>
            {cartItems.map((item) => (
              <tr key={item.id} className="border-b border-gray-700">
                <td className="py-4">
                  <img src={item.image} alt={item.title} className="w-10 h-10 mx-auto rounded-md" />
                </td>
                <td className="text-sm px-2">{item.title}</td>
                <td className="px-2">${item.price}</td>
                <td className="px-2">
                  <input
                    type="number"
                    min="1"
                    value={item.quantity}
                    className="w-12 text-center border border-gray-300 text-black rounded-sm"
                    readOnly
                  />
                </td>
                <td className="px-2">${item.price * item.quantity}</td>
                <td className="px-2">
                  <button className="hover:text-red-500">
                    <X size={18} />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <Footer />
    </div>
  );
};

export default Cart;
