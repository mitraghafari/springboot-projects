<ul class="nav nav-pills">
    <li class="nav-item">
        <a class="nav-link active" href="#">Active</a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Customers</a>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/getAllCustomers">Search</a>
            <a class="dropdown-item" href="#">Update</a>
        </div>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Cars</a>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/getcars">Search</a>
            <a class="dropdown-item" href="/insert-car-page">Insert</a>
            <a class="dropdown-item" href="#">Update</a>
        </div>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Logs</a>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">View</a>

        </div>
    </li>
    <li>
        ${sessionScope.hitCount}
    </li>

    <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
    </li>
</ul>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>