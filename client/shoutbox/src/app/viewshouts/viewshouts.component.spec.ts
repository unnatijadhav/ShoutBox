import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewshoutsComponent } from './viewshouts.component';

describe('ViewshoutsComponent', () => {
  let component: ViewshoutsComponent;
  let fixture: ComponentFixture<ViewshoutsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewshoutsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewshoutsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
