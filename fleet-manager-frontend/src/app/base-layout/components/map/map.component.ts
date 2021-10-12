import { Component, OnInit, ViewChild } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { icon, Marker } from 'leaflet';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
declare let L;
var map

var hashMap:Map<string, any> = new Map<string, any>();
@Component({
  selector: 'map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
  private types = ["COMPANIES", "FLEETS", "VEHICLES"];
  @ViewChild("departments") departments: any;
  @ViewChild("item") item: any;
  
  serverUrl = 'http://localhost:8080/socket';
  stompClient;
  positions: any = [];
  companies: any = [];
  filteredPositions: any = [];
  selectedDepartment = "COMPANIES";
  username: string = null;
  vehicles: any = [];
  fleets: any[];
  selectedItem: string = null;
  
  _departments: any[] = [];

  constructor(private http :HttpClient) { 
    var decode = jwt_decode(localStorage.getItem("token"));
    this.username = decode.sub;
    this.http.get("http://localhost:8080/vehicles/user/" + this.username).subscribe((vehicles:any) => {
          this.vehicles = vehicles;
          this.positions = this.vehicles.map(vehicle => {
              return {
                vehicleID: vehicle.vehicleID,
                companyInfo: vehicle.companyInfo,
                fleetInfo: vehicle.fleetInfo,
                x: vehicle.position.x,
                y: vehicle.position.y
              }
          });
          this.companies = this.vehicles.map(vehicle => {
            return vehicle.companyInfo.companyName
          });
          this.fleets = this.vehicles.map(vehicle => {
            return vehicle.fleetInfo.fleetName
          });
          this._departments = this.companies;
          console.log(this.fleets);
          

          this.filteredPositions = this.positions;
          this.filteredPositions.forEach((position: any) => {
              this.drawMarker(position);
          });
    });

    this.initializeWebSocketConnection();
    L.Icon.Default.prototype.options.iconUrl = 'assets/car2.png';
    L.Icon.Default.prototype.options.shadowUrl =  'assets/marker-shadow.png';
    L.Icon.Default.prototype.options.iconRetinaUrl =  'assets/car2.png';
  }

  ngOnInit() {
    map = L.map('map').setView([41.33, 19.82], 17);

    L.tileLayer('https://tiles.stadiamaps.com/tiles/osm_bright/{z}/{x}/{y}{r}.png', {
      attribution: '© <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
    }).addTo(map);
    
    var editableLayers = new L.FeatureGroup();
    map.addLayer(editableLayers);

    var drawPluginOptions = {
      position: 'topright',
      draw: {
        polygon: {
          allowIntersection: false, // Restricts shapes to simple polygons
          drawError: {
            color: '#e1e100', // Color the shape will turn when intersects
            message: '<strong>Oh snap!<strong> you can\'t draw that!' // Message that will show when intersect
          },
        shapeOptions: {
          color: '#97009c'
        }
        },
        // disable toolbar item by setting it to false
        polyline: false,
        circle: false, // Turns off this drawing tool
        rectangle: false,
        marker: false,
      },
      edit: {
        featureGroup: editableLayers, //REQUIRED!!
        remove: false
      }
  };

// Initialise the draw control and pass it the FeatureGroup of editable layers
  var drawControl = new L.Control.Draw(drawPluginOptions);
  map.addControl(drawControl);

  var editableLayers = new L.FeatureGroup();
  map.addLayer(editableLayers);

  map.on('draw:created', function(e) {
    var type = e.layerType,
    layer = e.layer;
    if (type === 'marker') {
      layer.bindPopup('A popup!');
    }
    console.log("Layer", layer.toGeoJSON());
    
    editableLayers.addLayer(layer);
    // var polygon = L.polygon([[1,1], [2,2], [3,3 ], [4,4]], {color: 'red'});
    // polygon.addTo(map);
  });
    
    // setInterval(()=>{
    //   map.removeLayer(marker);
    //   marker = L.marker([41.35, 19.82]).addTo(map);
    // }, 2000);

    map.on('click', ev => {
      var latlng = map.mouseEventToLatLng(ev.originalEvent);
      console.log(latlng.lat + ', ' + latlng.lng);
      var marker = L.marker([latlng.lat, latlng.lng]).addTo(map);
    });
  }

  drawMarker(position) {
    var marker = L.marker([position.x, position.y]).addTo(map);
    hashMap.set(position.vehicleID, marker);
  }

  onDepartmentChange(event){
    this.selectedDepartment = event.value;
    if(this.selectedDepartment ==="COMPANIES"){
      this._departments = this.companies;
    }else if(this.selectedDepartment ==="FLEETS"){
      this._departments = this.fleets;
    }
  }

  onItemChange(event){
    this.selectedItem = event.value;
  }

  filter() {
    console.log("Departments: ", this.selectedDepartment);
    if(this.selectedDepartment === "COMPANIES"){
      this.filteredPositions = this.positions.filter(position => {
        console.log("Positon is ", position);       
        return position.companyInfo.companyName.includes(this.selectedItem);
      });
    }else if(this.selectedDepartment === "FLEETS"){
      this.filteredPositions = this.positions.filter(position => {
        console.log("Positon is ", position);       
        return position.fleetInfo.fleetName.includes(this.selectedItem);
      });
    }
  }



  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/user/topic", (message: any) => {
        console.log(message.body);
        
    });

  });}
}