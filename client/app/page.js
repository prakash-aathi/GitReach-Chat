import { Dancing_Script } from 'next/font/google'
import 'bootstrap/dist/css/bootstrap.css'
const inter = Dancing_Script({ subsets: ['latin'] })

export default function Home() {
  return (
    <main className=''>
      <div className="container text-center mt-4 h-100 ">
        <h2>Welcome to GitReach Chat!</h2>
        <p>Simplify your developer conversations with GitReach Chat.</p>

        <div className='d-flex justify-content-center mt-5   align-items-center h-100'>
          <div className='w-50 h-50 bg-body-secondary rounded-2 shadow-sm  '>
            <h3 className='text-center text-danger pt-3 display-6 '>
              <div className={inter.className}>GitReach Chat</div>
            </h3>
            <p className='text-center '>Log in to your account</p>

            {/* login with github button */}
            <div className='d-flex justify-content-center align-items-center pb-4 '>
              <a href="http://localhost:8080/oauth2/authorization/github"
                className='btn btn-outline-danger btn-lg'>
                <i className='bi bi-github'></i> Login with GitHub
              </a>
            </div>

          </div>
        </div>

       </div>
    </main>
  )
}
