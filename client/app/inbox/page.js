import Compose from "@/components/Compose";
import Navbar from "@/components/Navbar";
import React from "react";

const page = () => {

  return (
    <div>
      {/* modal compose */}
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                New message
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <Compose />
            </div>
            {/* <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button type="submit" className="btn btn-primary">
                Send message
              </button>
            </div> */}
          </div>
        </div>
      </div>

      <Navbar />
      <div className="row">
        <div className="col-2 my-2 mx-2">
          <button type="button" className="btn btn-success  btn-lg  " data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">
            Compose
          </button>
          <div className="mt-2  ">
            <div className=" bg-body-secondary px-2 py-2 rounded d-flex justify-content-between  ">
              <div>Inbox</div>
              <span className="badge text-bg-secondary    ">4</span>
            </div>
            <div className=" bg-body-secondary px-2 py-2 rounded d-flex justify-content-between mt-2  ">
              <div>Sent</div>
              <span className="badge text-bg-secondary    ">4</span>
            </div>
          </div>
        </div>
        <div className="col-8"></div>
      </div>
    </div>
  );
};

export default page;
