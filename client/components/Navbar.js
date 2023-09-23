import { Dancing_Script } from "next/font/google";

const inter = Dancing_Script({ subsets: ["latin"] });

const Navbar = () => {
  return (
    <nav class="navbar bg-body-tertiary">
      <div class="container-fluid d-flex align-items-center  ">
        <a class="navbar-brand h6 text-success   ">
          <h3 className={inter.className}>GitReach Chat</h3 >
        </a>
        <form class="d-flex" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
          <button class="btn btn-outline-success" type="submit">
            Search
          </button>
        </form>
      </div>
    </nav>
  );
};

export default Navbar;
