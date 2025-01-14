import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/services/auth.guard';
import { AddParticipationComponent } from './components/add-participation/add-participation.component';
import { AddeventsComponent } from './components/addevents/addevents.component';
import { AddplayerComponent } from './components/addplayer/addplayer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EventlistComponent } from './components/eventlist/eventlist.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ParticipationListComponent } from './components/participation-list/participation-list.component';
import { ParticipationStatusComponent } from './components/participation-status/participation-status.component';
import { PlayerlistComponent } from './components/playerlist/playerlist.component';
import { SearchEventsComponent } from './components/search-events/search-events.component';
import { SearchSportsComponent } from './components/search-sports/search-sports.component';
import { SportslistComponent } from './components/sportslist/sportslist.component';

const routes: Routes = [
  {
  path:'',
  component:HomeComponent,
  pathMatch:'full'
},
{
  path:'login',
  component:LoginComponent,
  pathMatch:'full'
},
{
  path:'dashboard',
  component:DashboardComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'playerlist',
  component:PlayerlistComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'addplayer',
  component:AddplayerComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'event',
  component:EventlistComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'addevents',
  component:AddeventsComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'searchEvents',
  component:SearchEventsComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'sportslist',
  component:SportslistComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'searchSports',
  component:SearchSportsComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'participationList',
  component:ParticipationListComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'addParticipation',
  component:AddParticipationComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
},
{
  path:'participationStatus',
  component:ParticipationStatusComponent,
  pathMatch:'full',
  canActivate:[AuthGuard]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
