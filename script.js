
const eventList = [];
const output = document.getElementById('eventDataOutput');
const formResult = document.getElementById('formResult');

console.log('Script loaded: Local Community Event Portal');

function EventItem(name, type, seatsAvailable) {
  this.name = name;
  this.type = type;
  this.seatsAvailable = seatsAvailable;
}

EventItem.prototype.checkAvailability = function() {
  return this.seatsAvailable > 0;
};

const eventOne = new EventItem('Community Garden', 'Outdoor', 12);
const eventTwo = new EventItem('Park Cleanup', 'Volunteer', 0);
const eventThree = new EventItem('Storytelling', 'Workshop', 6);

eventList.push(eventOne, eventTwo, eventThree);
console.table(eventList);

const eventData = {
  title: 'Summer Block Party',
  attendees: 75,
  topics: ['music', 'food', 'games']
};

console.log('Object.entries example:', Object.entries(eventData));

function getAvailabilitySummary() {
  return eventList.map(item => `${item.name}: ${item.checkAvailability() ? 'Open' : 'Sold out'}`);
}

function setFormMessage(text, type = 'success') {
  formResult.innerHTML = `<div class="alert alert-${type}">${text}</div>`;
}

function validateRegistrationForm() {
  const nameInput = document.getElementById('name');
  const emailInput = document.getElementById('email');
  const eventType = document.getElementById('eventType');
  let valid = true;

  [nameInput, emailInput, eventType].forEach(field => {
    if (!field.value) {
      field.classList.add('invalid');
      valid = false;
    } else {
      field.classList.remove('invalid');
    }
  });

  return valid;
}

function saveRegistration(event) {
  event.preventDefault();
  if (!validateRegistrationForm()) {
    setFormMessage('Please complete all required fields before submitting.', 'danger');
    return;
  }

  const formValues = {
    name: document.getElementById('name').value,
    email: document.getElementById('email').value,
    eventType: document.getElementById('eventType').value,
    date: document.getElementById('eventDate').value,
    newsletter: document.getElementById('newsletter').checked
  };

  localStorage.setItem('communityRegistration', JSON.stringify(formValues));
  sessionStorage.setItem('lastRegistration', formValues.name);
  setFormMessage(`Thanks, ${formValues.name}! Your ${formValues.eventType} registration is recorded.`);
  console.log('Registration data saved to localStorage and sessionStorage', formValues);
}

function clearForm() {
  document.getElementById('registrationForm').reset();
  document.querySelectorAll('.invalid').forEach(field => field.classList.remove('invalid'));
  formResult.innerHTML = '<div class="alert alert-secondary">Form cleared.</div>';
}

function saveFeedback(event) {
  event.preventDefault();
  const commentField = document.getElementById('comment');
  const comment = commentField.value.trim();
  if (!comment) {
    commentField.classList.add('invalid');
    return;
  }
  commentField.classList.remove('invalid');
  document.getElementById('feedbackReply').innerHTML = `<div class="alert alert-success">Thanks! Your feedback has been received. "${comment}"</div>`;
  commentField.value = '';
  console.log('Feedback stored by callback example:', comment);
}

function showStorage() {
  const saved = JSON.parse(localStorage.getItem('communityRegistration') || '{}');
  const session = sessionStorage.getItem('lastRegistration') || 'No previous visitor stored.';
  document.getElementById('storageOutput').innerHTML = `
    <div class="card">
      <div class="card-body">
        <p><strong>localStorage:</strong> ${saved.name || 'None'} (${saved.eventType || 'None'})</p>
        <p><strong>sessionStorage:</strong> ${session}</p>
      </div>
    </div>`;
  console.log('Loaded storage values', { saved, session });
}

function saveToLocalStorage() {
  const example = { label: 'favoriteEvent', value: 'Neighborhood Picnic' };
  localStorage.setItem(example.label, example.value);
  document.getElementById('storageOutput').innerHTML = '<div class="alert alert-success">Saved favorite event in localStorage.</div>';
}

function saveToSessionStorage() {
  sessionStorage.setItem('sessionNote', 'Ready to help at the event booth');
  document.getElementById('storageOutput').innerHTML = '<div class="alert alert-warning">Saved a session note in sessionStorage.</div>';
}

function displayLocation(position) {
  const { latitude, longitude } = position.coords;
  const mapLink = `https://www.google.com/maps?q=${latitude},${longitude}`;
  document.getElementById('locationOutput').innerHTML = `Latitude: ${latitude.toFixed(4)}, Longitude: ${longitude.toFixed(4)} <br> <a href="${mapLink}" target="_blank">Open in Google Maps</a>`;
  document.getElementById('locationMap').innerHTML = `<iframe src="https://maps.google.com/maps?q=${latitude},${longitude}&output=embed" width="100%" height="280" style="border:0;" allowfullscreen="" loading="lazy"></iframe>`;
  console.log('Geolocation successful:', latitude, longitude);
}

function handleLocationError(error) {
  let message = 'Unable to retrieve location.';
  switch (error.code) {
    case error.PERMISSION_DENIED:
      message = 'User denied geolocation permission.';
      break;
    case error.POSITION_UNAVAILABLE:
      message = 'Location information is unavailable.';
      break;
    case error.TIMEOUT:
      message = 'Location request timed out.';
      break;
  }
  document.getElementById('locationOutput').textContent = message;
  console.warn('Geolocation error', error);
}

function requestLocation() {
  if (!navigator.geolocation) {
    document.getElementById('locationOutput').textContent = 'Geolocation not supported by this browser.';
    return;
  }
  navigator.geolocation.getCurrentPosition(displayLocation, handleLocationError);
}

function simulateAjaxCall() {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const success = true;
      if (success) {
        resolve({ message: 'AJAX simulation completed successfully.' });
      } else {
        reject(new Error('AJAX simulation failed.'));
      }
    }, 1200);
  });
}

async function loadEventData() {
  const spinner = document.getElementById('loadingSpinner');
  spinner.classList.remove('d-none');
  output.textContent = '';
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts/1');
    if (!response.ok) throw new Error('Network response was not ok');
    const data = await response.json();
    const ajaxResult = await simulateAjaxCall();
    output.innerHTML = `
      <div class="card">
        <div class="card-body">
          <h3>${data.title}</h3>
          <p>${data.body}</p>
          <p><strong>AJAX message:</strong> ${ajaxResult.message}</p>
        </div>
      </div>`;
    console.log('Fetch API and async/await demo result:', data);
  } catch (error) {
    output.innerHTML = `<div class="alert alert-danger">Error loading event data: ${error.message}</div>`;
    console.error('Fetch error:', error);
  } finally {
    spinner.classList.add('d-none');
  }
}

function createCounter(start) {
  let count = start;
  return function() {
    count += 1;
    return count;
  };
}

const registrationCounter = createCounter(0);
console.log('Closure demo count:', registrationCounter());
console.log('Closure demo count:', registrationCounter());

function displayAvailableEvents() {
  const available = eventList.filter(event => event.checkAvailability()).map(event => event.name);
  document.getElementById('eventDataOutput').innerHTML = `<p>Available events: ${available.join(', ')}</p>`;
}

document.getElementById('registrationForm').addEventListener('submit', saveRegistration);
document.getElementById('clearForm').addEventListener('click', clearForm);
document.getElementById('feedbackForm').addEventListener('submit', saveFeedback);
document.getElementById('saveStorage').addEventListener('click', saveToLocalStorage);
document.getElementById('saveSession').addEventListener('click', saveToSessionStorage);
document.getElementById('loadStorage').addEventListener('click', showStorage);
document.getElementById('locationBtn').addEventListener('click', requestLocation);
document.getElementById('spinnerBtn').addEventListener('click', loadEventData);

setTimeout(() => {
  console.log('Delayed callback example after 2 seconds');
  displayAvailableEvents();
}, 2000);

console.log('Prototype method checkAvailability on eventOne:', eventOne.checkAvailability());
console.warn('This warning simulates a debugging hint for event capacity.');
console.error('This error shows how console.error can be used in a debugging example.');
