import Footer from '../components/Footer'
import Navbar from '../components/Navbar'

const Landing = () => {

  const data=[
    {
        id:1,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i1.png",
        new_price:50.00,
        old_price:80.50,
      },
      {id:2,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i2.png",
        new_price:85.00,
        old_price:120.50,
      },
      {id:3,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i3.png",
        new_price:60.00,
        old_price:100.50,
      },
      {id:4,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i4.png",
        new_price:100.00,
        old_price:150.00,
      },
  ]
  return (
    <>
    <div className="w-full min-h-screen bg-gray-900 text-white">
      <Navbar/>
      <div className="flex flex-col lg:flex-row items-center justify-between pdg">
        <div className="">
          <h4 className='text-xl font-semibold uppercase'>new arrivals only</h4>
          <h4 className='flex items-center text-5xl pt-2 font-extrabold '>new 
            <img src="hand_icon.png" alt="hand" className='w-16' /> <br />
          </h4>
          <h4 className='text-5xl font-extrabold pt-2'>collextions
          </h4>
          <h4 className='text-5xl font-extrabold pt-2'>for everyone</h4>
          <button className='flex items-center gap-3 pt-5 hover:underline transition duration-300'>
          Latest Collections
          <img src="./arrow.png" alt="arrow" />
         </button>
        </div>
        <img src="./hero_image.png" alt="hero"
         className='w-1/3 pt-10' />
      </div>
      {/* popular in women */}
      <div className="pdg">
        <h1 className='text-center text-3xl font-semibold uppercase py-10'>popular in women</h1>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
          {
            data.map((item)=>(
              <div className="bg-gray-800 shadow p-3 space-y-1.5" key={item.id}>
                <img src={item.image} alt={item.name} className='w-full rounded' />
                <h2 className='font-semibold'>{item.name}</h2>
                <div className="flex items-center justify-between px-2">
                  <p>{item.new_price}$</p>
                   <p className=''>{item.old_price}$</p>
                </div>
              </div>
            ))
          }
        </div>
      </div>
      <div className="py-10"/>
      {/* exclusive */}
      <div className="bg-sky-900 w-full max-w-7xl rounded mx-auto p-4">
        <div className="flex flex-col lg:flex-row items-center justify-between px-4 sm:px-6 md:px-8 lg:px-10">
          <div className='space-y-3 uppercase'>
            <h2 className="text-3xl font-semibold lg:text-6xl">exclusive</h2>
            <h2 className="text-3xl font-semibold lg:text-6xl">offers for you</h2>
            <h5 className='pb-3'>only on best sellers products</h5>
            <button className='border border-gray-300 px-5 py-2
             rounded shadow cursor-pointer hover:bg-gray-300 hover:text-gray-900 transition duration-300'>Check now</button>
          </div>
          <img src="./exclusive_image.png" alt="exclusive_image" className='w-1/3' />
        </div>
      </div>
      {/* new collection */}
      <div className="">
        <h1 className='text-center text-3xl font-semibold uppercase py-10'>new collection</h1>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 pdg">
          {
            data.map((item)=>(
              <div className="bg-gray-800 shadow p-3 space-y-1.5" key={item.id}>
                <img src={item.image} alt={item.name} className='w-full rounded' />
                <h2 className='font-semibold'>{item.name}</h2>
                <div className="flex items-center justify-between px-2">
                  <p>{item.new_price}$</p>
                   <p className=''>{item.old_price}$</p>
                </div>
              </div>
            ))
          }
        </div>
      </div>
      {/*  */}
      <div className="bg-gradient-to-b from-gray-950 to-white/90 w-full max-w-7xl mx-auto p-6">
  <div className="text-center text-gray-900 space-y-5">
<h1 className="text-3xl md:text-4xl font-bold bg-gradient-to-r from-sky-500 to-indigo-600 bg-clip-text text-transparent">
  Get Exclusive Offers in Your Inbox
</h1>
    <p className="text-base md:text-lg">
      Subscribe to our newsletter and be the first to know about new arrivals, exclusive offers, and more!
    </p>

    <form className="flex flex-col md:flex-row items-center justify-center gap-3 mt-6">
      <input
        type="email"
        placeholder="Enter your email"
        className="w-full md:w-80 px-4 py-2 border border-gray-400 rounded-md focus:outline-none focus:ring-2 focus:ring-sky-400 transition"
      />
      <button
        type="submit"
        className="bg-gray-800 text-white px-6 py-2 rounded-md hover:bg-gray-700 transition duration-300 shadow"
      >
        Subscribe
      </button>
    </form>
  </div>
</div>

      {/* footer */}
      <div className="pt-10">
        <Footer/>
      </div>
    </div>
    </>
  )
}

export default Landing