import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClienteleComponent} from './clientele.component';

describe('ClienteleComponent', () => {
  let component: ClienteleComponent;
  let fixture: ComponentFixture<ClienteleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClienteleComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ClienteleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
