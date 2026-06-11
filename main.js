// Welcome

console.log("Welcome to the Community Portal");

window.onload = function () {

    alert("Page Loaded Successfully");

    loadPreference();

    displayEvents();
};

// Event Details

const eventName = "Music Festival";
const eventDate = "20-06-2026";

let seats = 50;

console.log(`${eventName} on ${eventDate} | Seats: ${seats}`);

seats--;

// Events Data

let events = [

    {
        id: 1,
        name: "Music Festival",
        category: "Music",
        seats: 50
    },

    {
        id: 2,
        name: "Sports Meet",
        category: "Sports",
        seats: 30
    },

    {
        id: 3,
        name: "Baking Workshop",
        category: "Workshop",
        seats: 20
    }

];

// Functions

function addEvent(eventObj) {

    events.push(eventObj);

    displayEvents();
}

function registerUser(userName) {

    console.log(userName + " Registered");
}

function filterEventsByCategory(category) {

    return events.filter(function (event) {

        return event.category === category;
    });
}

// Closure

function registrationCounter() {

    let count = 0;

    return function () {

        count++;

        return count;
    };
}

const counter = registrationCounter();

console.log(counter());
console.log(counter());

// Class

class Event {

    constructor(name, seats) {

        this.name = name;
        this.seats = seats;
    }
}

Event.prototype.checkAvailability = function () {

    return this.seats > 0;
};

let sampleEvent = new Event("Dance Show", 25);

console.log(sampleEvent.checkAvailability());

console.log(Object.entries(sampleEvent));

// Array Methods

events.push({

    id: 4,
    name: "Art Exhibition",
    category: "Art",
    seats: 40
});

let musicEvents = events.filter(function (event) {

    return event.category === "Music";
});

console.log(musicEvents);

let formattedEvents = events.map(function (event) {

    return "Workshop on " + event.name;
});

console.log(formattedEvents);

// Display Events

function displayEvents() {

    let container = document.querySelector("#eventContainer");

    if (!container) return;

    container.innerHTML = "";

    events.forEach(function (event) {

        let card = document.createElement("div");

        card.className = "eventCard";

        card.innerHTML = `

            <h3>${event.name}</h3>

            <p>Category : ${event.category}</p>

            <p>Seats : ${event.seats}</p>

            <button onclick="registerEvent(${event.id})">
                Register
            </button>
        `;

        container.appendChild(card);
    });
}

// Register Event

function registerEvent(id) {

    try {

        let selectedEvent = events.find(function (event) {

            return event.id === id;
        });

        if (selectedEvent.seats <= 0) {

            throw "No Seats Available";
        }

        selectedEvent.seats--;

        alert("Registration Successful");

        displayEvents();
    }

    catch (error) {

        alert(error);
    }
}

// Phone Validation

function validatePhone() {

    let phone = document.getElementById("phone").value;

    if (phone.length !== 10) {

        alert("Enter Valid Phone Number");
    }
}

// Event Fee

function showFee() {

    let eventType =
        document.getElementById("eventType").value;

    localStorage.setItem("eventType", eventType);

    let fee = 0;

    if (eventType === "Music") {

        fee = 100;
    }

    else if (eventType === "Workshop") {

        fee = 200;
    }

    else if (eventType === "Sports") {

        fee = 150;
    }

    document.getElementById("feeDisplay").innerHTML =
        "Event Fee : Rs. " + fee;
}

// Load Preference

function loadPreference() {

    let savedEvent =
        localStorage.getItem("eventType");

    if (savedEvent) {

        document.getElementById("eventType").value =
            savedEvent;

        showFee();
    }
}

// Character Counter

function countCharacters() {

    let text =
    document.getElementById("feedback").value;

    document.getElementById("charCount").innerText =
    text.length;
}

// Key Event

function typingMessage() {

    console.log("Typing...");
}
function videoReady() {

    document.getElementById("videoMessage").innerHTML =
    "Video Ready To Play";
}
// Confirmation

function showConfirmation() {

    document.getElementById("outputMessage")
        .value = "Registration Successful";
}

// Form

document.addEventListener("DOMContentLoaded", function () {

    let form =
        document.getElementById("registerForm");

    if (!form) return;

    form.addEventListener("submit", function (event) {

        event.preventDefault();

        let name =
            this.elements["name"].value;

        let email =
            this.elements["email"].value;

        if (name === "" || email === "") {

            alert("Please Fill All Fields");
        }

        else {

            alert("Form Submitted Successfully");
        }
    });
});

// Double Click Image

function enlargeImage(image) {

    image.style.transform = "scale(1.2)";
}

// Video Event

function videoReady() {

    document.getElementById("videoMessage")
        .innerHTML = "Video Ready To Play";
}

// Before Unload

window.onbeforeunload = function () {

    return "You have unsaved changes.";
};

// Storage

function clearPreferences() {

    localStorage.clear();

    sessionStorage.clear();

    alert("Preferences Cleared");
}

// Geolocation

function findLocation() {

    if (navigator.geolocation) {

        navigator.geolocation.getCurrentPosition(

            function (position) {

                document.getElementById("location")
                    .innerHTML =

                    `
                    Latitude :
                    ${position.coords.latitude}
                    <br>
                    Longitude :
                    ${position.coords.longitude}
                    `;
            },

            function (error) {

                alert(error.message);
            },

            {
                enableHighAccuracy: true,
                timeout: 5000
            }
        );
    }

    else {

        alert("Geolocation Not Supported");
    }
}

// Fetch API

fetch("https://jsonplaceholder.typicode.com/posts")

    .then(function (response) {

        return response.json();
    })

    .then(function (data) {

        console.log("Fetch Success");

        console.log(data.slice(0, 3));
    })

    .catch(function (error) {

        console.log(error);
    });

// Async Await

async function getEvents() {

    try {

        let response =
            await fetch(
                "https://jsonplaceholder.typicode.com/posts"
            );

        let data =
            await response.json();

        console.log("Async Data");

        console.log(data.slice(0, 2));
    }

    catch (error) {

        console.log(error);
    }
}

getEvents();

// Destructuring

const eventInfo = {

    title: "Community Workshop",
    category: "Workshop",
    seats: 35
};

const {

    title,
    category,
    seats: availableSeats

} = eventInfo;

console.log(title);
console.log(category);
console.log(availableSeats);

// Spread Operator

let copiedEvents = [...events];

console.log(copiedEvents);

// AJAX POST

function sendRegistration() {

    setTimeout(function () {

        fetch(
            "https://jsonplaceholder.typicode.com/posts",
            {

                method: "POST",

                headers: {
                    "Content-Type":
                        "application/json"
                },

                body: JSON.stringify({

                    user: "Community User"
                })
            }
        )

            .then(function (response) {

                return response.json();
            })

            .then(function (data) {

                console.log("Registration Sent");

                console.log(data);
            })

            .catch(function (error) {

                console.log(error);
            });

    }, 2000);
}

sendRegistration();

// jQuery

$(document).ready(function () {

    $("#registerBtn").click(function () {

        console.log("Register Button Clicked");
    });

    $(".eventCard").fadeIn();

    $(".eventCard").fadeOut(1000).fadeIn(1000);
});

// Framework Note

console.log(
    "React and Vue help create reusable UI components."
);