function MainOwner() {
  return (
    <div className="w-100 h-100 d-flex justify-content-center">
      <div className="w-25 text-center">
        <h1 className="mb-5">Owner</h1>
        <a href="/manage-equipment" className="btn btn-primary d-block mb-3">
          Manage equipment
        </a>
        <a href="/statistic-equipment" className="btn btn-primary d-block">
          Statistic equipment
        </a>
      </div>
    </div>
  );
}

export default MainOwner;
